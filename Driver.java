/** Driver Program
 *  @author Soorya Prasanna Ravichandran
 */

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner in;
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
		//Object for Timer function to calculate the efficiency of the code
	//	Timer t = new Timer();

		//Reading & printing the input graph
		Graph g = Graph.readGraph(in);
		System.out.println("Input Graph:");
		for(Vertex u: g) {
			System.out.print(u + ": ");
			for(Edge e: u.adj) {
				//Vertex v = e.otherEnd(u);
				System.out.print(e + " ");
			}
			System.out.println();
		}

		//Class to find the Euler Tour
		EulerTour et = new EulerTour();
		if(!et.isEulerian(g))
		{
			return;
		}

		CircularList<Vertex> tour;
		//Handling Tour which has 1 vertex and 0 edges
		if(g.size == 1)
		{
			tour = new CircularList<>();
			for(Vertex u : g.v)
			{
				if(u != null)
					tour.addNode(u);
			}
		}
		else
		{
			//Breaking the input graph into multiple sub-tours
		//	t.start();
			LinkedList<CircularList<Vertex>> subtours = null;
			subtours = (LinkedList<CircularList<Vertex>>) et.breakGraphIntoTours(g);
		//	t.end();
		//	System.out.println("Break into tours " + t);

			//Stitching all the multiple sub-tours into one Euler tour
		//	t.start();
			tour = et.stitchTours(subtours);
		//	t.end();
		//	System.out.println("Stitching sub-tours " + t);
		}

		//Printing the final Euler tour
		for(Vertex u: tour) 
		{ 
			System.out.println(u); 
		}

		//Validating the tour
	//	t.start();
		if(g.size > 1 && et.verifyTour(g, tour) == false)
		{
			System.out.println("Tour is invalid");
		}
	//	t.end();
	//	System.out.println("Verify tours " + t);
	}
}

/* Sample runs:
$ java Driver < in.no
Input Graph:
1: (1,2) (1,3) 
2: (1,2) (2,3) 
3: (2,3) (1,3) 
Not a bipartite graph

$ java Driver < in.yes
Input Graph:
1: (1,2) (1,4) 
2: (1,2) (2,3) 
3: (2,3) (3,4) 
4: (3,4) (1,4) 
Bipartite graph
 */