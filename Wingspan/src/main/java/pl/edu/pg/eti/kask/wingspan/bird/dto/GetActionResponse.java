package pl.edu.pg.eti.kask.wingspan.bird.dto;

import lombok.*;
import pl.edu.pg.eti.kask.wingspan.bird.entity.ActionType;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class GetActionResponse {
    private UUID id;
    private ActionType actionType;
    private String description;
}
