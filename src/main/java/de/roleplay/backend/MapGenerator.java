package de.roleplay.backend;

import de.roleplay.backend.dungonGenerator.mapgen.bsp.BspMapCreator;
import de.roleplay.backend.dungonGenerator.spawnentitys.Dungeon;
import de.roleplay.backend.entitys.Artefact;
import de.roleplay.backend.entitys.Monsters;
import de.roleplay.backend.entitys.PlayerEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Random;

import static de.roleplay.backend.entitys.MapType.*;

@RequiredArgsConstructor
public class MapGenerator {


	public static void generateAndPrintMap(){
		int height = 100;
		int width = 100;
		BspMapCreator bspMapCreator = new BspMapCreator();
		bspMapCreator.setMinRoomSize(5);
		bspMapCreator.setMaxIterations(6);
		bspMapCreator.setMapDimension(height, width);
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

	public DungonMap[][] generateAndReturnMap(int height, int width, int monster, int traps, int artefacts){
		BspMapCreator bspMapCreator = new BspMapCreator();
		bspMapCreator.setMinRoomSize(5);
		bspMapCreator.setMaxIterations(6);
		bspMapCreator.setMapDimension(height, width);
		bspMapCreator.setOut(null);
		char[][] dungon = bspMapCreator.createMap();
		Dungeon d = new Dungeon(dungon, monster, traps, artefacts);
		d.spawnPlayers();
		d.spawnMonsters();
		d.spawnTraps();
		d.spawnArtifacts();

		dungon = d.getDungeon();

		return toDungonMap(dungon, height, width);
	}

	private ArrayList<PlayerEntity> generatePlayer() {
		return new ArrayList<PlayerEntity>();
	}

	private ArrayList<Artefact> generateArtefacts() {
		Random random = new Random();
		ArrayList<Artefact> ret = new ArrayList<>();
		int rand = random.nextInt(2) + 1;
		for(int i = 0; i <= rand; i++){
			ret.add(new Artefact());
		}
		return ret;
	}

	private ArrayList<Monsters> generateMonster() {
		Random random = new Random();
		ArrayList<Monsters> ret = new ArrayList<>();
		int rand = random.nextInt(2) + 1;
		for(int i = 0; i <= rand; i++){
			ret.add(new Monsters());
		}
		return ret;
	}

	public DungonMap[][] toDungonMap(char[][] dungon, int height, int width){
		DungonMap[][] map = new DungonMap[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch (dungon[y][x]){
					case '~':
						map[y][x] = new DungonMap(
								OUTOFBOARDER,
								null,
								null,
								null
						);
						break;
					case ',':
						map[y][x] = new DungonMap(
								ROOM,
								null,
								null,
								null
						);
						break;
					case '.':
						map[y][x] = new DungonMap(
								FLORE,
								null,
								null,
								null
						);
						break;
					case '#':
						map[y][x] = new DungonMap(
								WALL,
								null,
								null,
								null
						);
						break;
					case '|':
						map[y][x] = new DungonMap(
								DOORV,
								null,
								null,
								null
						);
						break;
					case '-':
						map[y][x] = new DungonMap(
								DOORH,
								null,
								null,
								null
						);
						break;
					case 'M':
						map[y][x] = new DungonMap(
								MONSTER,
								generateMonster(),
								null,
								null
						);
						break;
					case 'A':
						map[y][x] = new DungonMap(
								ARTEFACT,
								null,
								generateArtefacts(),
								null
						);
						break;
					case 'K':
						map[y][x] = new DungonMap(
								KEY,
								null,
								null,
								null
						);
						break;
					case 'T':
						map[y][x] = new DungonMap(
								TRAP,
							null,
							null,
							null
					);
						break;
					case 'P':
						map[y][x] = new DungonMap(
								PLAYER,
								null,
								null,
								generatePlayer()
						);
						break;
					default:
						break;
				}
			}
		}
		return map;
	}
}
