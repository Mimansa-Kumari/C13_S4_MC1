package com.example.MuzixApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT ,reason = "No Track Exist With The Specified Condition!!!" )
public class NoTrackExistWithTheSpecifiedCondition extends Exception{
}
