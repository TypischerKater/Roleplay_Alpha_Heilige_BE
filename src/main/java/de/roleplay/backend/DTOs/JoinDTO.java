package de.roleplay.backend.DTOs;

import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@Validated
public class JoinDTO {
    @NonNull
    private UUID gameID;
    @NonNull
    private UUID PlayerID;
}
