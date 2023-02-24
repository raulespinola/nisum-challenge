package org.nisum.challenge.core.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "phone")
@Entity
@Where(clause = "is_active=true")
@NoArgsConstructor
public class PhoneEntity extends BaseEntity {

    @Column(name= "number")
    private String number;
    @Column(name= "citycode")
    private String cityCode;
    @Column(name= "countrycode")
    private String countryCode;
}
