package de.roleplay.backend.dungonGenerator.mapgen.bsp;


import de.roleplay.backend.dungonGenerator.shared.geom.IntRect;



public class CellNode {
	boolean horizontal;
	int depth;
	int x, y, width, height;

	CellNode left;
	CellNode right;

	IntRect room;
	IntRect corridor;

	public CellNode(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "CellNode{" +
		  "x=" + x +
		  ", y=" + y +
		  ", width=" + width +
		  ", height=" + height +
		  '}';
	}
}
