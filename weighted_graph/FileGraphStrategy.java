package weighted_graph;

import java.util.Scanner;

public class FileGraphStrategy implements CreateGraphStrategy {
	
	private Scanner scanner;

	public FileGraphStrategy(Scanner scanner){
			//scanner = new Scanner(new File(file));	
		this.scanner = scanner;
	}

	@Override
	public ListWeightedGraph create(int vertices) { // throws NumberFormatException
		ListWeightedGraph graph = new ListWeightedGraph(vertices);
		int weight;
		String str;
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				try {
					str = scanner.next();
					System.out.println(str);
					weight = Integer.parseInt(str);					
				} catch (NumberFormatException e){
					System.out.println("Input file contains non integer token\n");
					return null;
				}
				if (weight != 0) {
					graph.addEdge(i, j, weight);					
				}
			}
		}
		
		scanner.close();
		return graph;
	}

}
