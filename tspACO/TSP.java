package tspACO;

import sed.*;
import graph.*;
import antColony.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class of the program. It reads the input parameters and creates the
 * graph, the ant colony and the simulator. It also starts the simulation.
 * 
 * @autor Hugo Brites,Miguel Tavaresm João Góis.
 * @version 1.0
 * @since 2019-03-29
 */
public class TSP {

	protected static int maxWeight;
    protected static int antColonySize, colonyNest;
    protected static float finalInstant;
    protected static ISimulator simulator;
    protected static Parameters<Integer,Integer> parameters;

    /**
     * Main method of the program. It reads the input parameters and creates the
     * graph, the ant colony and the simulator. It also starts the simulation.
     * 
     * @param args - Input parameters.
     */
    public static void main(String[] args) {
        GraphCreator<Integer, Integer> context = new GraphCreator<Integer, Integer>();
        Scanner scanner = null;
        AntColony<Integer> antColony;
        parameters = new Parameters<Integer, Integer>();

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
                    } catch (NumberFormatException e) {
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
                    } catch (NumberFormatException e) {
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

        //print the initial parameters
        printInitialConditions();

        // Iniciate the simulator
        simulator.addEvPEC(new UpdateEvent<Integer>(finalInstant/20, simulator, parameters));
        simulator.simulate();
        //parameters.printCycles();

        System.exit(0);
    }
    
    /**
     * Calculates the sum of all the edges in the graph.
     * 
     * @return The sum of all the edges in the graph.
     */
    private static int calculateGraphWeight() {
        int sum = 0;
        for (int i = 0; i < parameters.graph.nrVertices(); i++) {
            for (int j = 0; j < parameters.graph.nrVertices(); j++) {
                sum += parameters.graph.getEdgeWeight(i, j);
            }
        }
        return sum;

    }
    
    /**
     * Prints the initial conditions of the simulation.
     */
    public static void printInitialConditions() {
    	System.out.println("Input parameters:");
    	System.out.printf("%-10s%10d%s\n", " ", parameters.graph.nrVertices(), "  :  number of nodes in the graph");
    	System.out.printf("%-10s%10d%s\n", " ", colonyNest, "  :  the nest node");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.alpha, "  :  alpha, ant move event");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.beta, "  :  beta, ant move event");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.delta, "  :  delta, ant move event");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.eta, "  :  eta, pheromone evaporation event");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.rho, "  :  rho, pheromone evaporation event");
    	System.out.printf("%-10s%10f%s\n", " ", parameters.pheroLevel, "  :  pheromone level");
    	System.out.printf("%-10s%10d%s\n", " ", antColonySize, "  :  ant colony size");
    	System.out.printf("%-10s%10f%s\n", " ", finalInstant, "  :  final instant");
        System.out.println();
        System.out.printf("%-5s%15s\n", " ", "with graph:");
        for(int i=0; i<parameters.graph.nrVertices(); i++) {
        	System.out.printf("%23s", " ");
            for(int j=0; j<parameters.graph.nrVertices(); j++) {
            	System.out.printf("%-4d", parameters.graph.getEdgeWeight(i+1, j+1));
            }
            System.out.print("\n");
        }
        System.out.println();
    }
}
