package com.noom.interview.fullstack.sleep.log.model;

import java.time.LocalTime;
import javax.validation.constraints.NotNull;

public record SleepLogRequest(
    @NotNull(message = "Time in bed from cannot be null") LocalTime timeInBedFrom,
    @NotNull(message = "Time in bed to cannot be null") LocalTime timeInBedTo,
    @NotNull(message = "Mood cannot be null") Mood mood) {}
