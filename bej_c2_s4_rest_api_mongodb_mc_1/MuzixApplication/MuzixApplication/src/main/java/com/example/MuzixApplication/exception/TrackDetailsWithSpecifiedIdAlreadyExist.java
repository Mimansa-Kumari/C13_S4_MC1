package com.example.MuzixApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT ,reason = "Track Details With Specified Id Already Exist!!!" )
public class TrackDetailsWithSpecifiedIdAlreadyExist extends Exception{
}
