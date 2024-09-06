**Euler Tour**

A graph G is called Eulerian if it is connected and the degree of every vertex is an even number. It is known that such graphs aways have a tour that goes through every edge of the graph exactly once. Such a tour is called an Euler tour.

**Description**
- The given graph is checked whether it is Eulerian or not. Pre- condition : Degree of every vertex is an even number.
- I have implemented Hierholzer’s algorithm to find the Euler tour.
- The algorithm states that to check if there exists a vertex u that belongs to the current tour but that has adjacent edges not part of the tour.
- Start another trail from u following unused edges until returning to u. This forms another sub-tour.
- Breakdown the input graph into multiple sub-tours and store them in the list breakGraphIntoTours that is of type circular list which can take all the vertex.
- Stitch all the multiple sub-tours into one Euler tour by iterating through each circular list of sub-tours (listOfTours)
- Verify the Euler tour by taking a tour and checking whether tour belongs to the graph. It return false if the tour is invalid (verifyTour)

**Methods implemented**
1. readGraph() - read the input graph
2. isEulerian() - check whether the graph is Eulerian or not
3. breakGraphIntoTours() - Breaking the input graph into multiple sub-tours
4. stitchTours() - Stitching all the multiple sub-tours into one Euler tour
5. verifyTour() - Validating the tour
6. getOutGoing() - to get the outgoing edges that are not part of the tour

**Results**
- Input 1 - Running Time : 1 ms (4k input)
- Input 2 - Running Time : 4 ms (40k input)
- Input 3 - Running Time : 8 ms (100k input)
- Input 4 - Running Time : 12 ms (150k input)
