package org.nisum.challenge.core.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationModel {
    private String uuid;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastModifiedDate;
    private OffsetDateTime lastLoginDate;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;
}
