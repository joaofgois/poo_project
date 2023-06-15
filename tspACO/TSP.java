package tspACO;

import sed.*;
import graph.*;
import antColony.*;
import expRandom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TSP {

    protected static float alpha, beta, delta;
    protected static float pheroLevel, eta, rho;
    protected static int nrPheroEvents, nrAntMoveEvents;
    protected static int antColonySize, colonyNest;
    protected static float finalInstant;
    protected WeightedGraph<Integer, Integer> graph;
    protected static float graphWeight; // falta isto
    protected static ISimulator simulator;
    protected ArrayList<Cycle<Integer, Integer>> hamiCycles;

    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> grafo;
        GraphCreator<Integer, Integer> context = new GraphCreator<Integer, Integer>();
        Scanner scanner;
        AntColony<Integer> antColony;
        AntMoveEvent<Integer> antMoveEvent;

        int inputVertices = 0;

        System.out.println(args.length);
        switch (args.length) {
            // Input from terminal without file.
            case 12:
                if (args[0].equals("-r")) {
                    inputVertices = Integer.parseInt(args[1]);
                    colonyNest = Integer.parseInt(args[3]);
                    alpha = Float.parseFloat(args[4]);
                    beta = Float.parseFloat(args[5]);
                    delta = Float.parseFloat(args[6]);
                    eta = Float.parseFloat(args[7]);
                    rho = Float.parseFloat(args[8]);
                    pheroLevel = Float.parseFloat(args[9]);
                    antColonySize = Integer.parseInt(args[10]);
                    finalInstant = Float.parseFloat(args[11]);

                    CreateGraphStrategy<Integer, Integer> generate;
                    generate = new RandomGraphStrategy(inputVertices);

                    context.setStrategy(generate);

                    grafo = context.createGraph(inputVertices);

                } else {
                    System.out.println("One or more input parameters are wrong.");
                }
                break;
            // Input from terminal with file.
            case 2:
                if (args[0].equals("-f")) {
                    // Try to open the file
                    try {
                        scanner = new Scanner(new File(args[1]));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                    CreateGraphStrategy<Integer, Integer> readfile;
                    // Read input from file
                    if (scanner.hasNextLine()) {
                        // skipping everything for now
                        scanner.nextLine();
                    }
                    readfile = new FileGraphStrategy(scanner);

                    context.setStrategy(readfile);

                    grafo = context.createGraph(inputVertices);
                } else {
                    System.out.println("One or more input parameters are wrong.");
                }
                break;
            // Incorrect number of input parameters.
            default:
                System.out.println("Number of input parameters is wrong.");
                System.exit(0);
                break;
        }

        simulator = new Simulator(finalInstant);
        grafo = context.createGraph(inputVertices);

        // Create the ant colony and the antMoveEvent
        antColony = new AntColony<>(colonyNest);
        for (int i = 0; i < antColonySize; i++) {
            antColony.addAnt(i);
            simulator.addEvPEC(new AntMoveEvent<Integer>(0, simulator, antColony, i, colonyNest, TSP.this));
        }

        // Iniciate the simulator
        simulator.addEvPEC(new UpdateEvent<Integer>(0, simulator, TSP.this));
        simulator.simulate();

    }

    public void storeCycle(ArrayList<Integer> cycle, int cycle_weight) {
        // ArrayList1.equals(ArrayList2) == true
        for (int i = 0; i < hamiCycles.size(); i++) {
            if (hamiCycles.get(i).weight > cycle_weight) {
                hamiCycles.add(i, new Cycle<Integer, Integer>(cycle, cycle_weight));
                return;
            }
            if (hamiCycles.get(i).weight == cycle_weight) {
                if (hamiCycles.get(i).cycle.equals(cycle)) {
                    return;
                }
            }
        }
        hamiCycles.add(new Cycle<Integer, Integer>(cycle, cycle_weight));
    }
}
