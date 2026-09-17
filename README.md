# Delivery Route Planner

A console-based Java project for modelling a delivery network and finding efficient routes. It was created as a university algorithms and data structures project.

## Features

- Model locations and weighted, bidirectional connections as a graph
- Calculate the shortest route with Dijkstra's algorithm
- Traverse reachable locations with depth-first search
- Sort directly connected destinations by distance using insertion sort
- Add new locations and connections through an interactive console menu
- Validate unknown locations and invalid distance values

## Project structure

- `Main.java` – interactive console application
- `Graph.java`, `Ort.java`, `Verbindung.java` – graph data model
- `DijkstraAlgorithmus.java` – shortest-path calculation
- `TiefensucheAlgorithmus.java` – depth-first traversal
- `InsertionSortAlgorithmus.java` – distance sorting

## Run locally

Requires Java 11 or newer.

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

The source code was compiled successfully before publication.
