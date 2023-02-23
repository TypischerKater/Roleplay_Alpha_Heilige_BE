package de.roleplay.backend.repositorys;

import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    GameEntity findByGameId(UUID id);
}
