package de.roleplay.backend.service;

import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.repositorys.GameRepository;
import de.roleplay.backend.repositorys.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameEntity createGame() {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(UUID.randomUUID());

        return gameRepository.saveAndFlush(gameEntity);
    }

}
