package de.roleplay.backend.dungonGenerator.spawnentitys;

import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
	private char[][] dungeon;
	private ArrayList<Room> rooms;
	private int numRooms;
	private int numTraps;
	private int numArtifacts;
	private int numMonster;

	public Dungeon(char[][] dungeon, int monster, int traps, int artifacts) {
		this.dungeon = dungeon;
		this.rooms = new ArrayList<>();
		this.numMonster = monster;
		this.numTraps = traps;
		this.numArtifacts = artifacts;
		for (int i = 0; i < dungeon.length; i++) {
			for (int j = 0; j < dungeon[i].length; j++) {
				if (dungeon[i][j] == ',') {
					rooms.add(new Room(i, j));
				}
			}
		}
		this.numRooms = rooms.size();
	}

	// Method to spawn players in a random room
	public void spawnPlayers() {
		Random rand = new Random();
		int playerRoom = rand.nextInt(numRooms);
		Room room = rooms.get(playerRoom);
		dungeon[room.getRow()][room.getCol()] = 'P';
	}

	// Method to spawn monsters in all rooms except the one containing players
	public void spawnMonsters() {
		Random rand = new Random();
		for (int i = 0; i < numMonster; i++) {
			int monsterRoom = rand.nextInt(numRooms);
			Room room = rooms.get(monsterRoom);
			if (dungeon[room.getRow()][room.getCol()] != 'P') {
				dungeon[room.getRow()][room.getCol()] = 'M';
			}
		}
	}

	// Method to spawn Traps in random rooms
	public void spawnTraps() {
		Random rand = new Random();
		for (int i = 0; i < numTraps; i++) {
			int TrapRoom = rand.nextInt(numRooms);
			Room room = rooms.get(TrapRoom);
			if (dungeon[room.getRow()][room.getCol()] != 'P') {
				dungeon[room.getRow()][room.getCol()] = 'T';
			}
		}
	}

	// Method to spawn artifacts in random rooms
	public void spawnArtifacts() {
		Random rand = new Random();
		for (int i = 0; i < numArtifacts; i++) {
			int artifactRoom = rand.nextInt(numRooms);
			Room room = rooms.get(artifactRoom);
			if (dungeon[room.getRow()][room.getCol()] != 'P') {
				dungeon[room.getRow()][room.getCol()] = 'A';
			}
		}
	}

	// Helper class to represent a room in the dungeon
	private class Room {
		private int row;
		private int col;

		public Room(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}
	}

	public char[][] getDungeon() {
		return dungeon;
	}
}