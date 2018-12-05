
# Travelling Salesman Problem
The problem we are trying to solve is the Travelling Salesman problem (TSP) which poses a situation that given a set of cities and distances between them, we find the shortest path for a route that visits each city and returns to the original city.

Our implementation approach for the problem is ‘Genetic Algorithm’. Such an approach is well suited for problems that involve huge solution spaces.

The approach involves calculating the list of chromosome by shuffling the genes to generate a population of individuals. We then calculate the fitness function of each individual and sort it. To evolve form one generation to other, mutation of cross breeded population is performed and the optimal solution is calculated.

To further improve the result, we have performed Parallelization using CompletableFuture and as final result, it displays the best route which the program has computed and draws a GUI as the output.

By Team 521 -- (Keyur Donga, Urvi Maru)
