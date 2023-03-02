package de.roleplay.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GameUpdateDTO {
    private String map;
    private int lastTurn;
    private String turnOrder;
}
