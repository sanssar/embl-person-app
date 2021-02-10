package com.embl.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PersonApplicationBadRequestException extends RuntimeException{
        public PersonApplicationBadRequestException(String detailMessage) {
                super(detailMessage);
        }
}
