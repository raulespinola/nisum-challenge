package org.nisum.challenge.controller.dto;

import lombok.*;
import org.nisum.challenge.controller.validator.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @Email(message = "Email is not valid", regexp ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Password
    private String password;
    private List<PhoneDto> phones;
}
