package pl.edu.pg.eti.kask.wingspan.user.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for returning a list of users via GET requests.
 *
 * <p>This DTO contains a simplified representation of users with only basic information
 * suitable for listing operations. Contains a nested User class for individual user data.</p>
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GetUsersResponse {

    /**
     * Simplified user representation for list responses.
     *
     * <p>Contains only essential user information needed for user listings.</p>
     */
    @Data
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    public static class User {
        /** Unique user identifier */
        private UUID id;

        /** User's login name */
        private String login;
    }

    /** List of users in the response */
    @Singular
    private List<User> users;
}
