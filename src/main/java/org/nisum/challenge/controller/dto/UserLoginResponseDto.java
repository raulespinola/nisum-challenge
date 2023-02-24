package org.nisum.challenge.controller.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {
    private String username;
    private Date expirationDateForToken;
    private String token;
}
