package org.nisum.challenge.core.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneModel {
    private String number;
    private String cityCode;
    private String countryCode;
}
