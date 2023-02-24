package org.nisum.challenge.controller.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequestDto {
    private String username;
    private String password;
}
