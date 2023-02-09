package de.roleplay.backend.repositorys;

import de.roleplay.backend.entitys.GameEntity;
import de.roleplay.backend.entitys.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
