package org.nisum.challenge.core.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@AllArgsConstructor
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    protected UUID id;
    @Column(name = "creation_date", nullable = false)
    protected OffsetDateTime creationDate;
    @Column(name = "last_modified_date", nullable = false)
    protected OffsetDateTime lastModifiedDate;
    @Column(name = "last_login_date", nullable = false)
    protected OffsetDateTime lastLoginDate;
    @Column(name = "active")
    protected Boolean active;

    protected BaseEntity(){
        creationDate = OffsetDateTime.now();
        lastModifiedDate = creationDate;
        lastLoginDate = creationDate;
        active = true;
    }

    public void setActive(Boolean active) {
        this.active = active == null || active.equals(FALSE) ? null : TRUE;
    }

    public Boolean isActive() {
        return active == null ? FALSE : active;
    }

    @PreUpdate
    protected void onUpdate(){
        lastModifiedDate = OffsetDateTime.now();
    }

    protected void onLogin(){
        lastLoginDate = OffsetDateTime.now();
    }
}
