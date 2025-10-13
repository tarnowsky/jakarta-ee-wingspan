package pl.edu.pg.eti.kask.wingspan.bird.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PatchBirdRequest {
    private Integer numOfCardsUnderCard;
    private Integer numOfEggs;
    private Integer numOfFoodOnCard;
}
