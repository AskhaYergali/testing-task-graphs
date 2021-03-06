# testing-task-graphs
## Solution for testing task with graphs. 
Project describes the solution to the given task - Trains and Towns. \
Used build tool - Maven, which includes dependencies and lets us to create an executable jar. 

Main class reads the data from the file input.txt, which has to be situated in the same directory as the project, or the jar file. \
The output of the project is written to the file named output.txt, after project performs countings. 

To process input data, we have to fill the file named input.txt with line with the same template as the the initial test set. \
Project might be run by creating a jar with -"mvn install" command, or by running Main class.

The main logic is described in three classes: Graph, CityNode, RouteFinder. \
Graph contains inside Set of CityNodes and operations to add or get them. \
CityNode implements node logic, contains inside name of city, list of cities in shortest path, \
counted distance and a map of all adjacement neighbours with weights to them. \
It provides the function of adding routes to the described map, and other methods used during the development. \
All countings are provided in RouteFinder class which takes initialized graph filled with nodes, \
which are alse filled with routes. 

Finding of the shortest route is implemented with Dijkstra Shortest Path Algorithm in method calculateShortestPathFromSource(), \
which finds the minimally weighted way from source to all destination nodes with O (N^2 + M). \
And other methods for counting routes for questions are provided \
In the last tenth question, there was implemented DFS (method calculateAllPathsFromStartToEnd()) \
using recursion, with limitations in total distance. 

Furthermore there could be provided a realization of logic with interfaces, \
especially instead of CityNode object in RouteFinder for further adaptability, \
and possibly separate Distance counter in case of further development. \
Also it is to mention that there is a JUnit library dependency described in pom.xml, and RouteFinderTest class, \
which used with the aim of testing the RouteFinder class.
