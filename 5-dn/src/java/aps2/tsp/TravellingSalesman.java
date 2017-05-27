package aps2.tsp;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TravellingSalesman {
	/**
	 * To solve the travelling salesman problem (TSP) you need to find a shortest
	 * tour over all nodes in the graph where each node must be visited exactly
	 * once. You need to finish at the origin node.
	 *
	 * In this task we will consider complete undirected graphs only with
	 * Euclidean distances between the nodes.
	 */

	private ArrayList<Point2D.Float> nodes = new ArrayList<>();
	private ArrayList<Integer> nodesId = new ArrayList<>();
	private Double[][] distance;


	private int[] shortestPath;
	private Double shortestDistance = Double.MAX_VALUE;
	
	/**
	 * Adds a node to a graph with coordinates x and y. Assume the nodes are
	 * named by the order of insertion.
	 * 
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public void addNode(int x, int y){
        nodes.add(new Point2D.Float(x,y));
        nodesId.add(nodesId.size());
        shortestDistance = Double.MAX_VALUE;

        distance = new Double[nodes.size()][nodes.size()];
        fillDistanceMatrix();

    }

    private void fillDistanceMatrix() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                distance[i][j] = nodes.get(i).distance(nodes.get(j));
                distance[j][i] = distance[i][j];
            }
        }
    }
	
	/**
	 * Returns the distance between nodes v1 and v2.
	 * 
	 * @param v1 Identifier of the first node
	 * @param v2 Identifier of the second node
	 * @return Euclidean distance between the nodes
	 */
	public double getDistance(int v1, int v2) {
		return distance[v1][v2];
	}


	
	/**
	 * Finds and returns an optimal shortest tour from the origin node and
	 * returns the order of nodes to visit.
	 * 
	 * Implementation note: Avoid exploring paths which are obviously longer
	 * than the existing shortest tour.
	 * 
	 * @param start Index of the origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] calculateExactShortestTour(int start) {
        if (shortestDistance != Double.MAX_VALUE)
            return shortestPath;

        ArrayList<Integer> nId = new ArrayList<>(nodesId);
        int startId = nId.remove(start);

        int[] left = new int[nId.size()];
		for (int i = 0; i < nId.size(); i++) {
			left[i] = nId.get(i);
		}

        int[] tour = new int[nodes.size()];
		tour[0] = startId;

		findShortestPath(tour, left, 1, 0.0);

        return shortestPath;
	}



	private void findShortestPath(int[] currentTour, int[] left, int level, Double pathLen) {
		// return if current path is longer than shortest
		if (pathLen >= shortestDistance) {
			return;
		}

		// check if path is completely built
		if (level > left.length) {
			// add distance from last point to start
			pathLen += getDistance(currentTour[0], currentTour[currentTour.length-1]);

			// replace path if new one is shorter
			if (pathLen < shortestDistance) {
				shortestPath = currentTour.clone();
				shortestDistance = pathLen;
			}
			return;
		}

		int len = left.length;

		// go only through half of points, so it skip checking reversed paths
		if (level == 1) {
			len = (len)/2 + 1;
		}

		for (int i = 0; i < len; i++) {
			if (left[i] != -1) {
				// add current point to path
				currentTour[level] = left[i];

				// remove current point, so others won't use it
				left[i] = -1;

				// add distance from last point to newly added point
				double currentPathLen = pathLen + getDistance(currentTour[level-1], currentTour[level]);

				findShortestPath(currentTour, left, level + 1, currentPathLen);

				// restore current point
				left[i] = currentTour[level];
			}
		}
		return;
	}
	
	/**
	 * Returns an optimal shortest tour and returns its distance given the
	 * origin node.
	 * 
	 * @param start Index of the origin node
	 * @return Distance of the optimal shortest TSP tour
	 */
	public double calculateExactShortestTourDistance(int start){
	    if (shortestDistance == Double.MAX_VALUE)
            calculateExactShortestTour(start);

        return shortestDistance;
	}	
	
	/**
	 * Returns an approximate shortest tour and returns the order of nodes to
	 * visit given the origin node.
	 * 
	 * Implementation note: Use a greedy nearest neighbor apporach to construct
	 * an initial tour. Then use iterative 2-opt method to improve the
	 * solution.
	 * 
	 * @param start Index of the origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] calculateApproximateShortestTour(int start){
        int[] tour = nearestNeighborGreedy(start);
        return  twoOptExchange(tour);
	}
	
	/**
	 * Returns an approximate shortest tour and returns its distance given the
	 * origin node.
	 * 
	 * @param start Index of the origin node
	 * @return Distance of the approximate shortest TSP tour
	 */
	public double calculateApproximateShortestTourDistance(int start){
		return calculateDistanceTravelled(calculateApproximateShortestTour(start));
	}
	
	/**
	 * Constructs a Hamiltonian cycle by starting at the origin node and each
	 * time adding the closest neighbor to the path.
	 * 
	 * Implementation note: If multiple neighbors share the same distance,
	 * select the one with the smallest id.
	 * 
	 * @param start Origin node
	 * @return List of nodes to visit in specific order
	 */
	public int[] nearestNeighborGreedy(int start){
        ArrayList<Integer> nodesLeft = new ArrayList<>(nodesId);
        int[] tour = new int[nodes.size()];
        int index = 0;

        tour[index] = nodesLeft.remove(start);

        while (!nodesLeft.isEmpty()) {

            // get last inserted point
            int before = tour[index];

            // set nearest to first node
            int nIndex = 0;
            double min = getDistance(before, nodesLeft.get(nIndex));

            // find closest neighbour
            for (int i = 1; i < nodesLeft.size(); i++) {
                Double distance = getDistance(before, nodesLeft.get(i));
                if (distance < min) {
                    min = distance;
                    nIndex = i;
                }
            }

            // add closest neighbour to tour, remove it from left nodes
            tour[++index] = nodesLeft.remove(nIndex);
        }

        return tour;
	}
	
	/**
	 * Improves the given route using 2-opt exchange.
	 * 
	 * Implementation note: Repeat the procedure until the route is not
	 * improved in the next iteration anymore.
	 * 
	 * @param route Original route
	 * @return Improved route using 2-opt exchange
	 */
	private int[] twoOptExchange(int[] route) {

        int[] newRoute;
        Double distance = calculateDistanceTravelled(route);

        for (int i = 0; i < route.length; i++) {
            for (int j = 0; j < route.length; j++) {
                // get new route with swap
                newRoute = twoOptSwap(route.clone(), i, j);
                Double newDistance = calculateDistanceTravelled(newRoute);

                if (newDistance < distance) {
                    distance = newDistance;
                    route = newRoute.clone();

                    // reset loops
                    i = 0;
                    j = 0;
                }
            }
        }

        return route;

	}
	
	/**
	 * Swaps the nodes i and k of the tour and adjusts the tour accordingly.
	 * 
	 * Implementation note: Consider the new order of some nodes due to the
	 * swap!
	 * 
	 * @param route Original tour
	 * @param i Name of the first node
	 * @param k Name of the second node
	 * @return The newly adjusted tour
	 */
	public int[] twoOptSwap(int[] route, int i, int k) {
		if (i == k) { return route;}
		if (i > k) { return twoOptSwap(route, k, i);}

		int[] newRoute = new int[route.length];

		// add nodes before first swept node
        for (int x = 0; x < i; x++) {
            newRoute[x] = route[x];
        }

		// add swept nodes and path between them
        for (int x = 0; x <= k-i; x++) {
            newRoute[x+i] = route[k-x];
        }

        // add the rest of nodes
        for (int x = k+1; x < route.length; x++) {
            newRoute[x] = route[x];
        }

        return  newRoute;
	}

	/**
	 * Returns the total distance of the given tour i.e. the sum of distances
	 * of the given path add the distance between the final and initial node.
	 * 
	 * @param tour List of nodes in the given order
	 * @return Travelled distance of the tour
	 */
	public double calculateDistanceTravelled(int[] tour){
		double distance = 0;

		// distance from first to last node
        for (int i = 0; i < tour.length-1; i++) {
            distance += getDistance(tour[i], tour[i+1]);
        }

        // distance from last to first
        distance += getDistance(tour[0], tour[tour.length-1]);

        return distance;
    }
	
}
