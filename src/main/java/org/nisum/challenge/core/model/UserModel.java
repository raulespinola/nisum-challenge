package org.nisum.challenge.core.model;

import lombok.*;
import org.nisum.challenge.core.entity.PhoneEntity;

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
    private List<PhoneEntity> phones;
}
