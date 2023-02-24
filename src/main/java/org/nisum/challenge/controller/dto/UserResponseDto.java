package org.nisum.challenge.controller.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String uuid;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private OffsetDateTime lastLoginDate;
    private String token;
    private Boolean isActive;
    private String username;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
