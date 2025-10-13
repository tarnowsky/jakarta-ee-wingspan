package pl.edu.pg.eti.kask.wingspan.user.dto;

import lombok.*;

/**
 * Data Transfer Object for updating a user's password via PUT requests.
 *
 * <p>This DTO is specifically designed for password change operations,
 * containing only the new password field for security purposes.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PutPasswordRequest {
    /** New password to set for the user */
    private String password;
}
