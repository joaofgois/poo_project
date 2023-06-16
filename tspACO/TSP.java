package tspACO;

import sed.*;
import graph.*;
import antColony.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TSP {

	protected static int maxWeight;
    protected static int antColonySize, colonyNest;
    protected static float finalInstant;
    protected static ISimulator simulator;
    protected static Parameters<Integer,Integer> parameters;

    public static void main(String[] args) {
        GraphCreator<Integer, Integer> context = new GraphCreator<Integer, Integer>();
        Scanner scanner = null;
        AntColony<Integer> antColony;
        parameters = new Parameters<Integer,Integer>();

        int inputVertices = 0;

        System.out.println(args.length);
        switch (args.length) {
            // Input from terminal without file.
            case 12:
                if (args[0].equals("-r")) {
                    try {
                        inputVertices = Integer.parseInt(args[1]);
                        maxWeight = Integer.parseInt(args[1]);
                        colonyNest = Integer.parseInt(args[3]);
                        parameters.alpha = Float.parseFloat(args[4]);
                        parameters.beta = Float.parseFloat(args[5]);
                        parameters.delta = Float.parseFloat(args[6]);
                        parameters.eta = Float.parseFloat(args[7]);
                        parameters.rho = Float.parseFloat(args[8]);
                        parameters.pheroLevel = Float.parseFloat(args[9]);
                        antColonySize = Integer.parseInt(args[10]);
                        finalInstant = Float.parseFloat(args[11]);
                    } catch (NumberFormatException e){
                        System.out.println("Input contains non integer/decimal token\n");
                        System.exit(1);
                    }
                    //Create Strategy to Create Graph
                    CreateGraphStrategy<Integer, Integer> generate;
                    generate = new RandomGraphStrategy(maxWeight);
                    context.setStrategy(generate);

                } else {
                    System.out.println("One or more input parameters are wrong.");
                    System.exit(1);
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
                        System.exit(1);
                    }
                    
                    //Read File Input
                    try {
                        inputVertices = Integer.parseInt(scanner.next());
                        colonyNest = Integer.parseInt(scanner.next());
                        parameters.alpha = Float.parseFloat(scanner.next());
                        parameters.beta = Float.parseFloat(scanner.next());
                        parameters.delta = Float.parseFloat(scanner.next());
                        parameters.eta = Float.parseFloat(scanner.next());
                        parameters.rho = Float.parseFloat(scanner.next());
                        parameters.pheroLevel = Float.parseFloat(scanner.next());
                        antColonySize = Integer.parseInt(scanner.next());
                        finalInstant = Float.parseFloat(scanner.next());
                    } catch (NumberFormatException e){
                        System.out.println("Input contains non integer/decimal token\n");
                        System.exit(1);
                    }

                    //Create Strategy to Create Graph
                    CreateGraphStrategy<Integer, Integer> readfile;
                    readfile = new FileGraphStrategy(scanner);
                    context.setStrategy(readfile);

                } else {
                    System.out.println("One or more input parameters are wrong.");
                    System.exit(1);
                }
                break;
            // Incorrect number of input parameters.
            default:
                System.out.println("Number of input parameters is wrong.");
                System.exit(1);
                break;
        }

        simulator = new Simulator(finalInstant);
        parameters.graph = context.createGraph(inputVertices);
        parameters.miu = calculateGraphWeight();

        // Create the ant colony and the antMoveEvent
        antColony = new AntColony<>(colonyNest);
        for (int i = 0; i < antColonySize; i++) {
            antColony.addAnt(i);
            simulator.addEvPEC(new AntMoveEvent<Integer>(0, simulator, antColony, i, colonyNest, parameters));
        }

        // Iniciate the simulator
        simulator.addEvPEC(new UpdateEvent<Integer>(0, simulator, parameters));
        simulator.simulate();
        //parameters.printCycles();

        System.exit(0);
    }
    
    private static int calculateGraphWeight() {
    	int sum = 0;
    	for(int i=0; i<parameters.graph.nrVertices(); i++) {
    		for(int j=0; j<parameters.graph.nrVertices(); j++) {
    			sum += parameters.graph.getEdgeWeight(i, j);
    		}
    	}
		return sum;
    	
    }
}
