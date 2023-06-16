package tspACO;

import expRandom.*;
import graph.*;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import antColony.*;
import sed.*;

//SO PARA DEBUG------------------------------------------------------------------


public class main_test {

	public static void main(String[] args) {
		CreateGraphStrategy<Integer,Integer> readfile;
		CreateGraphStrategy<Integer,Integer> generate;
		Scanner scanner;

		//try to open the file
		try {
			scanner = new Scanner(new File("src/tspACO/testgraph2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		//read input from file
		if (scanner.hasNextLine()) {
			//skipping everything for now
			scanner.nextLine();			
		}
		readfile = new FileGraphStrategy(scanner);
		generate = new RandomGraphStrategy(8);
		
		WeightedGraph<Integer, Integer> grafo;
		
		GraphCreator<Integer,Integer> context = new GraphCreator<Integer,Integer>();
		context.setStrategy(generate);
		context.setStrategy(readfile);
		
		grafo = context.createGraph(5);
		grafo.printGraph();
		
		//ExtRandom rand = ExtRandom.getInstance();
		//System.out.println(rand.nextExp(10));
		//System.out.println(rand.nextInt(10));
		//System.out.println(rand.nextDouble(10));
		//float[] nodes = new float[]{(float) 0.5, (float) 0.4, (float) 0.1};
		//System.out.println(rand.chooseRand(nodes));
		
		
		IAntColony<Integer> antcolony = new AntColony<Integer>(1);
		System.out.println("Colony nest: " + antcolony.colonyNest());
		
		antcolony.addAnt(0);

		System.out.println();
		
		//TspACOSimulation<Integer, Integer> parent = new TspACOSimulation<Integer, Integer>(grafo, 1000, 1, (float) 1.0, (float)1.0, (float)0.2, (float)2.0, (float)10.0, (float)0.5);
		Simulator simulator = new Simulator(200);
		Parameters<Integer, Integer> parameters = new Parameters<Integer,Integer>();
        parameters.graph = grafo;
        parameters.alpha = (float)1.0;
        parameters.beta = (float)1.0;
        parameters.delta = (float)0.2;
        parameters.pheroLevel = (float)2.0;
        parameters.eta = (float)10.0;
        parameters.rho = (float)0.5;
        parameters.miu = 10;
		Event ev1 = new AntMoveEvent<Integer>(2, simulator, antcolony, 0, 1, parameters );
		Event ev2 = new AntMoveEvent<Integer>(0, simulator, antcolony, 1, 1, parameters);
        Event ev7 = new UpdateEvent<Integer>(0, simulator, parameters);
		//antcolony.setPheromone(1, 0, 2);
		//Event ev2 = new PheroEvent<Integer>(3, parent.simulator, antcolony, parent, 0, 1);

        simulator.addEvPEC(ev1);
        simulator.addEvPEC(ev2);
        simulator.addEvPEC(ev7);
		//simulator.simulate();
		//System.out.println("--" + parent.hamiCycles.get(0));
		//parameters.printCycles();
		
		/*
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(15);
		Cycle<Integer,Integer> ciclo = new Cycle<Integer,Integer>(list,12);
		System.out.println(ciclo);
		*/
		
	}

}










