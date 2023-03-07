package de.roleplay.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		int height = 100;
		int width = 100;
		int monster = 15;
		int traps = 20;
		int artefacts = 10;
		MapGenerator.generateAndPrintMap();

		/*DungonMap[][] dungon = MapGenerator.generateAndReturnMap(height, width, monster, traps, artefacts);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(dungon[y][x]);
			}

			System.out.println();
		}*/

		//MapGenerator.generateAndPrintMap();//here for test reasons
		SpringApplication.run(BackendApplication.class, args);
	}

}
