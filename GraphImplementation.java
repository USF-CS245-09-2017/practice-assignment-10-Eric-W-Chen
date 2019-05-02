import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphImplementation implements Graph{
	private int[] v; //array of vertices
	private int[][] edges; //matrix
	private int vertices;

	public GraphImplementation(int vertices){
		v = new int[vertices];
		this.vertices = vertices;
		edges = new int[vertices][vertices];

	}

	public void addEdge(int src, int target){
		edges[src][target] = 1;
	}

	public List<Integer> topologicalSort (){
		ArrayList<Integer> stack = new ArrayList<Integer>();
		boolean visited[] = new boolean[vertices];
		for(int i=0; i<vertices; i++){
			visited[i] = false; //initialize all of visited to false
		}
		for(int i=0; i<vertices; i++){
			if(visited[i] == false){
				topologicalSortH(i, visited, stack); //call helper function going through i vertex's
			}
		}
		return stack; 
	}

	public void topologicalSortH(int vertex, boolean visited[], ArrayList<Integer> stack){

		visited[vertex] = true;
		int[] myNeighbors = neighbors(vertex);
		for(int i=0; i<myNeighbors.length; i++){
			if(!visited[myNeighbors[i]]){ //if it's never visited, it'll enter this conditional
				topologicalSortH(myNeighbors[i], visited, stack);
			}
		}
		stack.add(0, vertex); //adds to front of list
	}

	public int[] neighbors (int vertex){
		int count = 0;

		for(int i=0; i<vertices; i++){
			if (edges[vertex][i] == 1){
				count++;
			}
		}
		int[] myNeighbors = new int[count]; //making array of vertex with edges
		int index = 0;

		for(int i=0; i<vertices; i++){ 
			if (edges[vertex][i] == 1 && edges[vertex][i] != vertex){ //making sure vertex isn't pointing to itself in directed graph
				myNeighbors[index] = i;
				index++;
			}
		}
		return myNeighbors;
	}
}
