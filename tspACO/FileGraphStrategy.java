package tspACO;

import java.util.Scanner;

import graph.*;

public class FileGraphStrategy implements CreateGraphStrategy<Integer,Integer> {
	
	private Scanner scanner;

	public FileGraphStrategy(Scanner scanner){
			//scanner = new Scanner(new File(file));	
		this.scanner = scanner;
	}

	@Override
	public MapWeightedGraph<Integer,Integer> create(int vertices) { // throws NumberFormatxception
		MapWeightedGraph<Integer,Integer> graph = new IntMapWeightedGraph();
		for (int i = 0; i < vertices; i++) {
			graph.addVertex(i+1);
		}
		int weight;
		String str;
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				try {
					str = scanner.next();
					//System.out.println(str); //para tirar
					weight = Integer.parseInt(str);
				} catch (NumberFormatException e){
					System.out.println("Input file contains non integer token\n");
					return null;
				}
				if (weight != 0) {
					graph.addEdge(i+1, j+1, weight);					
				}
			}
		}
		
		scanner.close();
		return graph;
	}

}
