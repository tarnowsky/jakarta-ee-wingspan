package pl.edu.pg.eti.kask.wingspan.user.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PatchUserRequest {
     /** User's login name */
    private String login;

    /** User's avatar image path */
    private String avatarPath;

    /** User's birthdate */
    private LocalDate birthdate;
}
