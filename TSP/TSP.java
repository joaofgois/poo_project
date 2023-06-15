package TSP;

import sed.*;
import weighted_graph.*;
import antColony.*;
import expRandom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TSP {
    // private PriorityQueue<Cycle<Integer,Integer>> bestCycles;
    protected float alpha, beta, delta;
    protected float pheroLevel, eta, rho;
    protected int nrPheroEvents, nrAntMoveEvents;
    protected int antColonySize;
    protected double finalInstant;
    protected WeightedGraph<Integer, Integer> graph;
    protected float graphWeight; // falta isto
    protected Simulator simulator;

    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> grafo;
        Scanner scanner;
        GraphCreator context = new GraphCreator();
        int inputVertices = 0;

        System.out.println(args.length);
        switch (args.length) {
            // Input from terminal without file.
            case 12:
                if (args[0].equals("-r")) {
                    inputVertices = Integer.parseInt(args[1]);
                    CreateGraphStrategy generate;
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
                    inputVertices = Integer.parseInt(scanner.nextLine());
                    CreateGraphStrategy readfile;
                    // Try to open the file
                    try {
                        scanner = new Scanner(new File(args[1]));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
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

        grafo = context.createGraph(inputVertices);

    }
}
