/**
 *  Euler Tour Implementation
 *  @author Soorya Prasanna Ravichandran
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EulerTour
{
	//private HashSet<Edge> traversedEdges = new HashSet<>();
	private HashSet<Vertex> table = new HashSet<>();

	//Function to check whether the given graph is Eulerian or not
	public boolean isEulerian(Graph g)
	{
		//Check if Eulerian path exists
		Vertex src = g.v.get(1);	//Vertex at index 0 is not used

		//checking if connected
		g.bfs(src);
		for(Vertex u: g.v) 
		{
			if(u != null)
			{
				if(!u.seen)
				{
					System.out.println("Graph is not Eulerian");
					break;
				}
			}
		}

		/*	
		 * 	Pre-condition : degree of every vertex is an even number (by Eulers tour)
		 *	checking number of vertices with odd degree
		 */
		int odd = 0;
		for(Vertex u: g.v)
		{
			if(u != null)
			{
				if(u.adj.size()%2 != 0)
				{	
					odd++;
				}
			}
		}
		if(odd > 0)
		{
			System.out.println("Graph is not Eulerian");
			return false;
		}
		else
		{
			//System.out.println("Graph is Eulerian");
			return true;
		}
	}	

	/*
	 * 	Check if there exists a vertex u that belongs to the current tour 
	 * 	but that has adjacent edges not part of the tour, start another trail from u
	 *  following unused edges until returning to u. This forms another sub-tour
	 */
	private Vertex getOutGoing(Graph g)
	{
		if(table.isEmpty())
		{
			Vertex src = null;

			for(Vertex u: g.v)
			{
				if(u != null)
				{
					src = u;
					break;
				}
			}

			return src;
		}
		else
		{
			Iterator<Vertex> it = table.iterator();
			Vertex temp;

			while(it.hasNext())
			{
				temp = it.next();

				for(Edge e: temp.adj)
				{
					if(!e.traversed)
					{
						return temp;
					}
				}
			}
		}

		return null;
	}

	//Breaking the input graph into mutilple sub-tours
	public List<CircularList<Vertex>> breakGraphIntoTours(Graph g)
	{
		LinkedList<CircularList<Vertex>> subtours = new LinkedList<>();
		Vertex src = null;

		while((src = getOutGoing(g)) != null)
		{
			Vertex temp = null;
			CircularList<Vertex> clist = new CircularList<>();
			clist.addNode(src);
			table.add(src);
			Vertex v = src;
			while(temp != src)
			{
				Iterator<Edge> adj_iterator = v.iterator();
				Edge e;
				while(adj_iterator.hasNext())
				{
					e = adj_iterator.next();
					if(!e.traversed)
					{
						e.traversed = true;

						v = e.otherEnd(v);

						temp = v;
						break;
					}
				}
				if(temp != src)
				{
					if(!table.contains(v))
					{
						table.add(v);
					}

					clist.addNode(v);
				}
			}
			subtours.addLast(clist);
		}
		return subtours;
	}

	/*	
	 * 	Stitching all the multiple sub-tours into one Euler tour
	 * 	by iterating through each circular list of sub-tours
	 */
	public CircularList<Vertex> stitchTours(LinkedList<CircularList<Vertex>> subtours)
	{
		Vertex v1 = null, v2 = null;

		while(subtours.size() > 1)
		{
			CircularList<Vertex> subtour1, subtour2;
			subtour1 = subtours.removeFirst();
			subtour2 = subtours.removeFirst();

			//stitching subtour1 and subtour2 and adding them to the front
			CircularList<Vertex> stitchedList = new CircularList<>();

			Vertex subtour2_head = subtour2.head.data;

			Iterator<Vertex> subtour1it = subtour1.iterator();
			while(subtour1it.hasNext())  
			{
				v1 = subtour1it.next();

				if(v1 != subtour2_head)
				{
					stitchedList.addNode(v1);
				}
				else
				{
					break;
				}
			}

			Iterator<Vertex> subtour2it = subtour2.iterator();
			while(subtour2it.hasNext())
			{
				v2 = subtour2it.next();
				stitchedList.addNode(v2);
			}

			Vertex v3;
			boolean foundV1 = false;
			subtour1it = subtour1.iterator();
			while(subtour1it.hasNext())
			{
				v3 = subtour1it.next();

				if(v3 == v1)
				{
					foundV1 = true;
				}

				if(foundV1)
				{
					stitchedList.addNode(v3);
				}
			}

			subtours.addFirst(stitchedList);
		}

		return subtours.get(0);
	}

	//Validating the final Euler tour
	public boolean verifyTour(Graph g, CircularList<Vertex> tour)
	{
		long numberOfEdges = g.getNumberOfEdges();

		Iterator<Vertex> it = tour.iterator();
		Vertex first;
		Vertex v1 = it.next(), v2;
		first = v1;
		Edge e;

		while(it.hasNext())
		{
			v2 = it.next();

			e = v1.adjMap.get(v2);

			if(e.traversed)
			{
				e.traversed = false;
				numberOfEdges--;
			}
			else
			{
				return false;
			}

			v1 = v2;
		}

		//removing last edge
		e = v1.adjMap.get(first);

		if(e.traversed)
		{
			e.traversed = false;
			numberOfEdges--;
		}
		else
		{
			return false;
		}

		if(numberOfEdges == 0)
		{
			return true;
		}

		return false;
	}
}
