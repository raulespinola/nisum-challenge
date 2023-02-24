package org.nisum.challenge.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "UQ_email_constraint")})
@Entity
@Where(clause = "active=true")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name= "token")
    private String token;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return uuid != null && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}