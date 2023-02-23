package de.roleplay.backend;

import de.roleplay.backend.dungonGenerator.mapgen.bsp.BspMapCreator;
import de.roleplay.backend.dungonGenerator.spawnentitys.Dungeon;

public class MapGenerator {

	public static void generateAndPrintMap(){
		int height = 100;
		int width = 100;
		BspMapCreator bspMapCreator = new BspMapCreator();
		bspMapCreator.setMinRoomSize(5);
		bspMapCreator.setMaxIterations(6);
		bspMapCreator.setMapDimension(height, width);
		//bspMapCreator.setOut(System.out);
		bspMapCreator.setOut(null);
		char[][] dungon = bspMapCreator.createMap();
		Dungeon d = new Dungeon(dungon, 15, 20, 10);
		d.spawnPlayers();
		d.spawnMonsters();
		d.spawnTraps();
		d.spawnArtifacts();

		dungon = d.getDungeon();


		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(dungon[y][x]);
			}

			System.out.println();
		}
	}

	public static char[][] generateAndReturnMap(int height, int width, int monster, int traps, int artefacts){
		BspMapCreator bspMapCreator = new BspMapCreator();
		bspMapCreator.setMinRoomSize(5);
		bspMapCreator.setMaxIterations(6);
		bspMapCreator.setMapDimension(height, width);
		//bspMapCreator.setOut(System.out);
		bspMapCreator.setOut(null);
		char[][] dungon = bspMapCreator.createMap();
		Dungeon d = new Dungeon(dungon, monster, traps, artefacts);
		d.spawnPlayers();
		d.spawnMonsters();
		d.spawnTraps();
		d.spawnArtifacts();

		dungon = d.getDungeon();

		return dungon;
	}

}
