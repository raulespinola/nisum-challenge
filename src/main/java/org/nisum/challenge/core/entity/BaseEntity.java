package org.nisum.challenge.core.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Getter
@Setter
@ToString
@AllArgsConstructor
@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID uuid;
    @Column(name = "creation_date", nullable = false)
    protected OffsetDateTime creationDate;
    @Column(name = "last_modified_date", nullable = false)
    protected OffsetDateTime lastModifiedDate;
    @Column(name = "last_login_date", nullable = false)
    protected OffsetDateTime lastLoginDate;
    @Column(name = "is_active")
    protected Boolean isActive;

    protected BaseEntity(){
        creationDate = OffsetDateTime.now();
        lastModifiedDate = creationDate;
        lastLoginDate = creationDate;
        isActive = true;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive == null || isActive.equals(FALSE) ? null : TRUE;
    }

    public Boolean isActive() {
        return isActive == null ? FALSE : isActive;
    }

    @PreUpdate
    protected void onUpdate(){
        lastModifiedDate = OffsetDateTime.now();
    }

    protected void onLogin(){
        lastLoginDate = OffsetDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return uuid != null && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
