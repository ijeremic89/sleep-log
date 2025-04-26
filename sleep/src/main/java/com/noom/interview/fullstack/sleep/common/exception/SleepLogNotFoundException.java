package com.noom.interview.fullstack.sleep.common.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SleepLogNotFoundException extends RuntimeException {

  public SleepLogNotFoundException(Long userId, LocalDate date) {
    super(
        "Sleep log not found for userId: "
            + userId
            + " on "
            + date.format(DateTimeFormatter.ISO_DATE));
  }
}
