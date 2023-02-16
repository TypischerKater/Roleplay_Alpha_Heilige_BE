package de.roleplay.backend.dungonGenerator.mapgen;

import de.roleplay.backend.dungonGenerator.shared.geom.IntPoint;

import java.util.Map;
import java.util.function.Consumer;



public interface CharLevelMapCreator<T extends Consumer> {
	char[][] createMap();

	Map<IntPoint, Integer> getRoomsByTile();

	void processRooms(T roomCallback);
}
