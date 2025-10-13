package pl.edu.pg.eti.kask.wingspan.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Entity for system user. Represents information about particular user as well as credentials for authorization and
 * authentication needs.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class User implements Serializable {

    /**
     * Unique identifier of the user.
     */
    private UUID id;

    private List<String> userRoles;

    /**
     * User's avatar.
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String avatarPath;

    /**
     * User's login name for authentication.
     */
    private String login;

    /**
     * User's password for authentication. Excluded from toString() for security reasons.
     */
    @ToString.Exclude
    private String password;

    /**
     * User's date of birth.
     */
    private LocalDate birthdate;
}
