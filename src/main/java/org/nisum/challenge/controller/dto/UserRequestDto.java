package org.nisum.challenge.controller.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
