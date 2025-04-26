package com.noom.interview.fullstack.sleep.common.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SleepLogAlreadyExistsException extends RuntimeException {
  public SleepLogAlreadyExistsException(Long userId, LocalDate date) {
    super(
        "Sleep log already exists for user "
            + userId
            + " on "
            + date.format(DateTimeFormatter.ISO_DATE));
  }
}
