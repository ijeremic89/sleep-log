package com.noom.interview.fullstack.sleep.log.model;

import java.time.LocalTime;

public record SleepLogRequest(LocalTime timeInBedFrom, LocalTime timeInBedTo, Mood mood) {}
