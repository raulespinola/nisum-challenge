package org.nisum.challenge.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {

      private String number;
      private String cityCode;
      private String countryCode;
}
