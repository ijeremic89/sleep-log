package com.noom.interview.fullstack.sleep.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Long userId) {
    super("User with ID " + userId + " not found.");
  }
}
