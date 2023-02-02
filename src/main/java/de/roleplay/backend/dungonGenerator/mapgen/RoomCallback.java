package de.roleplay.backend.dungonGenerator.mapgen;

import de.roleplay.backend.dungonGenerator.shared.geom.IntPoint;
import de.roleplay.backend.dungonGenerator.shared.geom.IntRect;

import java.util.List;

public interface RoomCallback {
	void processRoom(int room, IntRect roomRect, List<IntPoint> tiles);
}
