package de.roleplay.backend.dungonGenerator;

import de.roleplay.backend.DungonMap;
import de.roleplay.backend.entitys.PlayerEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.roleplay.backend.entitys.MapType.*;

@Service
public class MapCoverService {
	private int width = 100;
	private int height = 100;

	private DungonMap[][] map;
	// Konstruktor und andere Methoden ...
	public DungonMap[][] updateVisibleArea(DungonMap[][] map, int radius, UUID spieleruuid) {
		PlayerEntity playerEntity;

		int playerX = 0;
		int playerY = 0;

		for (DungonMap[] row : map) {
			for (DungonMap element : row) {
				if(element.getFieldType().equals(PLAYER)){
					for(PlayerEntity player : element.getPlayers()){
						if(player.getPlayerId().toString().equals(spieleruuid.toString())){
							playerEntity = player;
							playerX = playerEntity.getXPosition();
							playerY = playerEntity.getYPosition();
						}
					}
				}
			}
		}

		this.map = map;

		List<Point> visibleElements = new ArrayList<>();

		// Berechnen der Grenzen des sichtbaren Bereichs
		int minX = Math.max(0, playerX - radius);
		int minY = Math.max(0, playerY - radius);
		int maxX = Math.min(width - 1, playerX + radius);
		int maxY = Math.min(height - 1, playerY + radius);

		// Iterieren durch alle Felder innerhalb des Radius
		List<Point> visibleArea = new ArrayList<>();
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				int dx = x - playerX;
				int dy = y - playerY;
				if (dx * dx + dy * dy <= radius * radius) {
					// Das Feld liegt innerhalb des sichtbaren Bereichs
					visibleArea.add(new Point(x, y));
				}
			}
		}

		// Durchlaufen der Dungeon-Karte und hinzufügen sichtbarer Wände, Türen und Außenkanten zur Liste
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map[x][y].getFieldType().equals(WALL) || map[x][y].getFieldType().equals(DOORH) || map[x][y].getFieldType().equals(DOORV) || map[x][y].getFieldType().equals(OUTOFBOARDER)) {
					Point p = new Point(x, y);
					if (visibleArea.contains(p)) {
						visibleElements.add(p);
					}
				}
			}
		}

		// Durchlaufen der Dungeon-Karte und Ausblenden nicht sichtbarer Felder
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Point p = new Point(x, y);
				if (map[x][y].getFieldType().equals(FLORE) || map[x][y].getFieldType().equals(ROOM)) {
					if (!visibleArea.contains(p)) {
						// Das Feld ist nicht sichtbar
						int dx = x - playerX;
						int dy = y - playerY;
						boolean isVisible = false;
						for (Point visiblePoint : visibleElements) {
							int vdx = visiblePoint.x - playerX;
							int vdy = visiblePoint.y - playerY;
							if ((dx * vdy - dy * vdx) * (dx * vdy - dy * vdx) <= radius * radius * (vdx * vdx + vdy * vdy)) {
								// Das Feld ist auf dem Vektor zwischen dem Spieler und einem sichtbaren Punkt
								isVisible = true;
								break;
							}
						}
						if (!isVisible) {
							// Das Feld ist nicht sichtbar und muss ausgeblendet werden
							map[x][y].setFieldType(BLANK);
						}
					}
				}
			}
		}
		return map;
	}
}
