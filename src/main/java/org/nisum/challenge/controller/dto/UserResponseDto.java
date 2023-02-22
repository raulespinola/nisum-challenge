package org.nisum.challenge.controller.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String uuid;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastUpdateDate;
    private OffsetDateTime lastLogin;
    private String token;
    private boolean isActive;
}
