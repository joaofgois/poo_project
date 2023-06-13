package weighted_graph;

import expRandom.*;
import java.io.*;
import java.util.Scanner;

public class main_test {

	public static void main(String[] args) {
		CreateGraphStrategy readfile;
		CreateGraphStrategy generate;
		Scanner scanner;

		//try to open the file
		try {
			scanner = new Scanner(new File("src/weighted_graph/testgraph.txt"));
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
		generate = new RandomGraphStrategy(15);
		
		WeightedGraph<Integer, Integer> grafo;
		
		GraphCreator context = new GraphCreator();
		context.setStrategy(generate);
		context.setStrategy(readfile);
		
		grafo = context.createGraph(5);
		grafo.printGraph();
		
		ExpRandom rand = ExpRandom.getInstance();
		System.out.println(rand.nextExp(10));
		System.out.println(rand.nextInt(10));
		System.out.println(rand.nextDouble(10));
	}

}
