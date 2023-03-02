package de.roleplay.backend.repositorys;

import de.roleplay.backend.entitys.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    void deleteByPlayerId(UUID id);

    Optional<PlayerEntity> findByPlayerId(UUID id);

    ArrayList<PlayerEntity> findByGameId(UUID id);

    @Query(value = "SELECT COUNT(playerId) FROM player WHERE gameId = :id", nativeQuery = true)
    int countPlayer(UUID id);
}
