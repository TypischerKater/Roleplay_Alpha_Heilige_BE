package de.roleplay.backend;

import de.roleplay.backend.entitys.Artefact;
import de.roleplay.backend.entitys.Monsters;
import de.roleplay.backend.entitys.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class DungonMap {
	boolean wall;
	boolean flore;
	boolean room;
	boolean doorH;
	boolean doorV;
	boolean monster;
	ArrayList<Monsters> monsters;
	boolean artefact;
	ArrayList<Artefact> artefacts;
	boolean key;
	boolean player;
	ArrayList<PlayerEntity> players;
	boolean outOfBoarder;
}
