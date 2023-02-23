package org.nisum.challenge.core.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;
}
