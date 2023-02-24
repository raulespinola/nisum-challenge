package org.nisum.challenge.core.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginModel {
    private String username;
    private OffsetDateTime lastLoginDate;
    private String token;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Date expirationDateForToken;
    private Boolean isActive;
    private String email;
}
