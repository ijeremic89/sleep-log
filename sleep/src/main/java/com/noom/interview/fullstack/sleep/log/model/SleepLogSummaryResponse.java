package com.noom.interview.fullstack.sleep.log.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public record SleepLogSummaryResponse(
    LocalDate fromDate,
    LocalDate toDate,
    Long avgTotalTimeInBedSeconds,
    LocalTime avgTimeInBedFrom,
    LocalTime avgTimeInBedTo,
    Map<Mood, Long> moodFrequencies) {}
