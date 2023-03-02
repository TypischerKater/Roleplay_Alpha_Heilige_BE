package de.roleplay.backend.entitys;

import lombok.Data;

@Data
public class Artefact {

	Enum<ArtefactType> type;

	public Artefact(){
		int typeCount = ArtefactType.values().length;

	}
}
