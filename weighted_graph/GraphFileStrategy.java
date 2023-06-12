package weighted_graph;

import java.io.*;
import java.util.Scanner;

public class GraphFileStrategy implements CreateGraphStrategy {
	
	Scanner scanner;

	public GraphFileStrategy(String file) {
		try {
			scanner = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ListWeightedGraph create(int vertices) { // throws NumberFormatException
		ListWeightedGraph graph = new ListWeightedGraph();
		int weight;
		
		
		//skip first line
		if (scanner.hasNextLine()) {
			scanner.nextLine();			
		}
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				try {
					weight = Integer.parseInt(scanner.next());					
				} catch (NumberFormatException e){
					System.out.println("Input file contains non integer token\n");
					return null;
				}
				if (weight != 0) {
					graph.addEdge(i, j, weight);					
				}
			}
			//skip newline character
			scanner.next();
			if (! scanner.hasNextLine()) {
				System.out.println("Input file has wrong number of edges\n");
				return null;
			}
		}
		
		scanner.close();
		return graph;
	}

}
