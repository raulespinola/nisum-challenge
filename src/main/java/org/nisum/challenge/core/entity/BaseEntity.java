package org.nisum.challenge.core.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Long id;
    @Column(name = "creation_date", nullable = false)
    protected OffsetDateTime creationDate;
    @Column(name = "last_update_date", nullable = false)
    protected OffsetDateTime lastUpdateDate;
    @Column(name = "active")
    protected Boolean active;

    protected BaseEntity(){
        creationDate = OffsetDateTime.now();
        lastUpdateDate = creationDate;
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
        lastUpdateDate = OffsetDateTime.now();
    }
}
