package de.roleplay.backend.repositorys;

import de.roleplay.backend.DungonMap;
import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findByGameId(UUID id);

	@Query(value = "SELECT map FROM game WHERE gameId = :uuid", nativeQuery = true)
	String getMapByGameId(UUID uuid);
}
