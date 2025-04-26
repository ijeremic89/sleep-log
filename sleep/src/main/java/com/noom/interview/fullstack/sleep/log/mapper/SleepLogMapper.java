package com.noom.interview.fullstack.sleep.log.mapper;

import com.noom.interview.fullstack.sleep.log.model.*;
import java.time.LocalTime;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SleepLogMapper {
  SleepLogMapper INSTANCE = Mappers.getMapper(SleepLogMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  @Mapping(target = "sleepDate", ignore = true)
  @Mapping(target = "totalTimeInBed", ignore = true)
  SleepLogEntity toEntity(SleepLogRequest request);

  @Mapping(
      target = "totalTimeInBedInSeconds",
      expression = "java(entity.getTotalTimeInBed().getSeconds())")
  SleepLogResponse toResponse(SleepLogEntity entity);

  @Mapping(
      target = "avgTimeInBedFrom",
      source = "avgTimeInBedFromSeconds",
      qualifiedByName = "secondsToLocalTime")
  @Mapping(
      target = "avgTimeInBedTo",
      source = "avgTimeInBedToSeconds",
      qualifiedByName = "secondsToLocalTime")
  @Mapping(target = "moodFrequencies", source = "summary", qualifiedByName = "mapMoodFrequencies")
  SleepLogSummaryResponse toSummaryResponse(SleepLogSummaryProjection summary);

  @Named("secondsToLocalTime")
  static LocalTime secondsToLocalTime(Long seconds) {
    if (seconds == null) return null;

    if (seconds >= 86400) {
      seconds -= 86400;
    }

    return LocalTime.ofSecondOfDay(seconds);
  }

  @Named("mapMoodFrequencies")
  static Map<Mood, Long> mapMoodFrequencies(SleepLogSummaryProjection summary) {
    return Map.of(
        Mood.GOOD, summary.getGoodMoodCount(),
        Mood.OK, summary.getOkMoodCount(),
        Mood.BAD, summary.getBadMoodCount());
  }
}
