package de.roleplay.backend;

import de.roleplay.backend.entitys.Artefact;
import de.roleplay.backend.entitys.MapType;
import de.roleplay.backend.entitys.Monsters;
import de.roleplay.backend.entitys.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class DungonMap {
	Enum<MapType> fieldType;
	ArrayList<Monsters> monsters;
	ArrayList<Artefact> artefacts;
	ArrayList<PlayerEntity> players;
}
