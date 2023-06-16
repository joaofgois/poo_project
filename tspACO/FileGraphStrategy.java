package tspACO;

import java.util.Scanner;

import graph.*;

/**
 * This class reads the file input and creates a graph with the information read.
 * 
 * @author Hugo Brites, Miguel Tavares e João Góis.
 */
public class FileGraphStrategy implements CreateGraphStrategy<Integer,Integer> {
	
	private Scanner scanner;

	/**
	 * Functions that creates a new scanner to read a file.
	 * 
	 * @param scanner Scanner object that reads the file.
	 */
	public FileGraphStrategy(Scanner scanner) {
		//scanner = new Scanner(new File(file));	
		this.scanner = scanner;
	}

	/**
	 * Function that creates a graph with the information read from the file.
	 * 
	 * @param vertices Number of vertices of the graph.
	 * @return Graph created.
	 */
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
					System.out.println("Input file contains has wrong formatting\n");
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
