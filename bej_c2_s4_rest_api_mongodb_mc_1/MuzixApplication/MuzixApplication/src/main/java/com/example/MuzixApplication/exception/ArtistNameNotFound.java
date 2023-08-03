package com.example.MuzixApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT ,reason = "Artist Name Not Found!!!" )
public class ArtistNameNotFound extends Exception{
}
