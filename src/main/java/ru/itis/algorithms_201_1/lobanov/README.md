This algorithm has two realizations.
First one chooses one random node and then passes through the entire adjacency matrix of the graph and selects the minimum edge, 
then adds an edge and the second vertex of this edge to our MST, then, taking into account all the edges that are not included in the MST, 
finds the minimum edge again and does these actions until all the vertices are in the MST.
The complexity of this realization is O(V^2) where V is a number of nodes in graph.

Second one chooses one random node and then adds all incident edges to the binary heap, then selects the minimum, 
takes the second vertex of this edge and adds all edges that are incident to it, if such an edge already exists, 
then it is deleted, so repeat until the binary heap becomes empty.
The complexity of this realization is O(EVlogV) where V is a number of nodes in graph and E is a number of edges in graph.
