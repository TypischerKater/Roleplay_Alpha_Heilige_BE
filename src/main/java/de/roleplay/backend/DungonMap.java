package de.roleplay.backend;

import de.roleplay.backend.entitys.Artefact;
import de.roleplay.backend.entitys.MapType;
import de.roleplay.backend.entitys.Monsters;
import de.roleplay.backend.entitys.PlayerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DungonMap {
	Enum<MapType> fieldType;
	ArrayList<Monsters> monsters;
	ArrayList<Artefact> artefacts;
	ArrayList<PlayerEntity> players;

	public DungonMap removePlayer(PlayerEntity playerEntity){
		this.players.remove(playerEntity);
		return this;
	}

	public DungonMap addPlayer(PlayerEntity playerEntity){
		this.players.add(playerEntity);
		return this;
	}

	public DungonMap addMonster(){
		// todo needs to be filed when monster is implemented
		return this;
	}

	public DungonMap removeMonster(){
		//todo same as above
		return this;
	}
}
