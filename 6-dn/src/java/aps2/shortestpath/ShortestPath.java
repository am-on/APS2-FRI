package aps2.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

class Edge {
	String source;
	String target;
	int weight;

	Edge (String source, String target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

}

public class ShortestPath {
	/**
	 * In this task you need to find a shortest path on a given directed graph
	 * with arbitrary edge lengths (including negative weights!) from a single
	 * source node to all other nodes in the graph.
	 * 
	 * Your algorithm should also detect, if there is a negative cycle in the
	 * graph and return null in this case.
	 */

	private Vector<String> nodes;
	private ArrayList<Edge> edges;
	private HashMap<String, Integer> price;
	private HashMap<String, String> predecessor;
	private String start;
	private boolean negativeCycle;

	ShortestPath() {
		nodes = new Vector<>();
		edges = new ArrayList<>();
		start = "";
	}

	/**
	 * Adds a new node named s to the graph.
	 * 
	 * @param s Name of the node
	 * @return True, if a node is unique and successfully added; False otherwise.
	 */
	public boolean addNode(String s) {
		return !nodes.contains(s) && nodes.add(s);
	}
	
	/**
	 * Returns names of all nodes in the graph.
	 * 
	 * @return Names of all nodes in the graph.
	 */
	public Vector<String> getNodes() {
		return nodes;
	}
	
	/**
	 * Adds an edge from node a to node b.
	 * 
	 * @param a Source node.
	 * @param b Target node.
	 * @param w Edge weight. Warning: The weight can also be negative!
	 */
	public void addEdge(String a, String b, int w) {
		if (nodes.contains(a) && nodes.contains(b)) {
			for (Edge edge : edges) {
				if (edge.source.equals(a) && edge.target.equals(b)) {
					edge.weight = w;
					return;
				}
			}
			edges.add(new Edge(a,b,w));
		}
	}

	/**
	 * Computes and locally stores shortest paths from the given origin node
	 * to all other nodes in the graph.
	 * 
	 * @param start Origin node.
	 */
	public void computeShortestPaths(String start) {
		this.start = start;
		negativeCycle = false;

		price = initNodes(start);
		predecessor = new HashMap<>();

		boolean change = false;
		for (int i = 0; i < nodes.size(); i++) {

			for (Edge edge : edges) {

				// prevent integer overflow
				if (price.get(edge.source) == Integer.MAX_VALUE) {
					continue;
				}

				int newPrice = price.get(edge.source) + edge.weight;

				if (newPrice < price.get(edge.target)) {
					price.put(edge.target, newPrice);
					predecessor.put(edge.target, edge.source);
					change = true;
				}
			}

			if (!change) {
				return;
			}
		}

		for (Edge edge : edges) {
			// prevent integer overflow
			int newPrice = (price.get(edge.source) == Integer.MAX_VALUE) ? Integer.MAX_VALUE : price.get(edge.source) + edge.weight;
			if (newPrice < price.get(edge.target)) {
				price.put(edge.target, newPrice);
				predecessor.put(edge.target, edge.source);
				negativeCycle = true;
			}
		}
	}

	/**
	 * Set price to origin node to 0 and price of other nodes to MAX_INT.
	 * @param start Origin node
	 * @return HashMap with initialized prices
	 */
	private HashMap<String, Integer> initNodes(String start) {
		HashMap<String, Integer> price = new HashMap<>();

		for (int i = 0; i < nodes.size(); i++) {
			price.put(nodes.get(i), Integer.MAX_VALUE);
		}

		// set price to origin node to 0
		price.put(start, 0);

		return price;
	}
	
	/**
	 * Returns a list of nodes on the shortest path from the given origin to
	 * destination node. Returns null, if a path does not exist or there is a
	 * negative cycle in the graph.
	 * 
	 * @param start Origin node
	 * @param dest Destination node
	 * @return List of nodes on the shortest path from start to dest or null, if the nodes are not connected or there is a negative cycle in the graph.
	 */
	public Vector<String> getShortestPath(String start, String dest) {
		if (!this.start.equals(start)) {
			computeShortestPaths(start);
		}

		if (negativeCycle || price.get(dest) == Integer.MAX_VALUE) {
			return null;
		}

		Vector<String> path = new Vector<>();

		String location = dest;
		while (!location.equals(start)) {
			path.add(0, location);
			location = predecessor.get(location);
		}

		path.add(0, start);

		return path;
	}
	
	/**
	 * Returns the distance of the shortest path from the given origin to
	 * destination node. Returns Integer.MIN_VALUE, if a path does not exist
	 * or there is a negative cycle in the graph.
	 * 
	 * @param start Origin node
	 * @param dest Destination node
	 * @return Distance of the shortest path from start to dest, Integer.MIN_VALUE, if there is a negative cycle in the graph, or Integer.MAX_VALUE, if the nodes are not connected.
	 */
	public int getShortestDist(String start, String dest) {
		if (!this.start.equals(start)) {
			computeShortestPaths(start);
		}
		if (negativeCycle) {
			return Integer.MIN_VALUE;
		}
//		else if (price.get(dest) == Integer.MAX_VALUE){
//			return Integer.MAX_VALUE;
//		}

//		int distance = 0;
//		String location = dest;
//		while (!location.equals(start)) {
//			distance += getEdgeDistance(predecessor.get(location), location);
//			location = predecessor.get(location);
//		}
//
//		return distance;
		return price.get(dest);
	}

	private int getEdgeDistance(String u, String v) {
		int minDistance = Integer.MAX_VALUE;
		for (Edge edge : edges) {
			if (edge.source.equals(u) && edge.target.equals(v)) {
				if (minDistance > edge.weight) {
					minDistance = edge.weight;
				}
				minDistance = edge.weight;
			}
		}
		return minDistance;
	}
}
