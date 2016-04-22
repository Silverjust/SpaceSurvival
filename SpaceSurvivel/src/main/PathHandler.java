package main;

import pathfinder.*;
import processing.core.PApplet;

public class PathHandler {
	private Graph g;
	private Game game;
	private PApplet app;

	GraphNode[] gNodes, rNodes;
	GraphEdge[] gEdges, exploredEdges;
	IGraphSearch pathFinder;
	GraphNode startNode, endNode;
	private long time;
	private int start;
	private int end;
	private float nodeSize;

	// FIXME ADD Laufweg algorithmus
	public PathHandler(Game game) {
		// des hab ich aus dem pathfinder beispiel
		this.game = game;
		this.app = game.app;
		g = new Graph();
		makeGraph();
		start = 107;
		end = 500;
		g.compact();
		gNodes = g.getNodeArray();
		gEdges = g.getAllEdgeArray();
		// Create a path finder object based on the algorithm
		pathFinder = new GraphSearch_Astar(g, new AshCrowFlight(1.0f));
		usePathFinder(pathFinder);

		System.out.println("PathHandler.PathHandler()");
	}

	public void makeGraph() {
		boolean allowDiagonals = true;

		int tilesX = (int) Game.gridW;
		int tilesY = (int) Game.gridH;
		int dx = (int) Game.gridSize;
		int dy = (int) Game.gridSize;
		int sx = dx / 2, sy = dy / 2; // use deltaX to avoid horizontal
										// wraparound edges
		int deltaX = tilesX + 1; // must be > tilesX

		float hCost = dx, vCost = dy, dCost = PApplet.sqrt(dx * dx + dy * dy);
		float cost = 0;
		int px, py, nodeID, col;
		GraphNode aNode;

		py = sy;
		for (int y = 0; y < tilesY; y++) {
			nodeID = deltaX * y + deltaX;
			px = sx;
			for (int x = 0; x < tilesX; x++) { // Calculate the cost
				// col = backImg.get(px, py) & 0xFF;
				cost = 1;

				col = 1;// test

				// If col is not black then create the node and edges
				if (col != 0) {
					aNode = new GraphNode(nodeID, px, py);
					g.addNode(aNode);
					if (x > 0) {
						g.addEdge(nodeID, nodeID - 1, hCost * cost);
						if (allowDiagonals) {
							g.addEdge(nodeID, nodeID - deltaX - 1, dCost * cost);
							g.addEdge(nodeID, nodeID + deltaX - 1, dCost * cost);
						}
					}
					if (x < tilesX - 1) {
						g.addEdge(nodeID, nodeID + 1, hCost * cost);
						if (allowDiagonals) {
							g.addEdge(nodeID, nodeID - deltaX + 1, dCost * cost);
							g.addEdge(nodeID, nodeID + deltaX + 1, dCost * cost);
						}
					}
					if (y > 0)
						g.addEdge(nodeID, nodeID - deltaX, vCost * cost);
					if (y < tilesY - 1)
						g.addEdge(nodeID, nodeID + deltaX, vCost * cost);
				}
				px += dx;
				nodeID++;
			}
			py += dy;
		}
	}

	void usePathFinder(IGraphSearch pf) {
		time = System.nanoTime();
		pf.search(start, end, true);
		time = System.nanoTime() - time;
		rNodes = pf.getRoute();
		exploredEdges = pf.getExaminedEdges();
	}

	void drawNodes() {
		app.pushStyle();
		app.noStroke();
		app.fill(255, 0, 255, 72);
		for (GraphNode node : gNodes)
			app.ellipse(node.xf(), node.yf(), nodeSize, nodeSize);
		app.popStyle();
	}

	void drawEdges(GraphEdge[] edges, int lineCol, float sWeight) {
		if (edges != null) {
			app.pushStyle();
			app.noFill();
			app.stroke(lineCol);
			app.strokeWeight(sWeight);
			for (GraphEdge ge : edges) {
				app.line(ge.from().xf(), ge.from().yf(), ge.to().xf(), ge.to().yf());
			}
			app.popStyle();
		}
	}

	void drawRoute(GraphNode[] r, int lineCol, float sWeight) {
		if (r.length >= 2) {
			app.pushStyle();
			app.stroke(lineCol);
			app.strokeWeight(sWeight);
			app.noFill();
			for (int i = 1; i < r.length; i++)
				app.line(r[i - 1].xf(), r[i - 1].yf(), r[i].xf(), r[i].yf());
			// Route start node
			app.strokeWeight(2.0f);
			app.stroke(0, 0, 160);
			app.fill(0, 0, 255);
			app.ellipse(r[0].xf(), r[0].yf(), nodeSize, nodeSize);
			// Route end node
			app.stroke(160, 0, 0);
			app.fill(255, 0, 0);
			app.ellipse(r[r.length - 1].xf(), r[r.length - 1].yf(), nodeSize, nodeSize);
			app.popStyle();
		}
	}

	public boolean isObstacle(float x, float y) {
		// boolean b = updater.map.collision.pixels[(int) (x + y *
		// ref.updater.map.collision.width)] == 0x00;
		return false;
	}

	public void draw() {
		drawEdges(gEdges, app.color(192, 192, 192, 128), 1.0f);
		drawEdges(exploredEdges, app.color(0, 0, 255), 1.8f);
		drawNodes();
		drawRoute(rNodes, app.color(200, 0, 0), 5.0f);

	}
}
