package com.example.MuzixApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT ,reason = "Invalid Track Id!!!" )
public class InvalidTrackId extends Exception{
}
