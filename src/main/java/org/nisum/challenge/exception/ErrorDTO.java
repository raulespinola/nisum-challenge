package org.nisum.challenge.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ErrorDTO {

    private HttpStatus estado;
    private String mensaje;

}