package com.noom.interview.fullstack.sleep.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter
public class DurationConverter implements AttributeConverter<Duration, Long> {
  @Override
  public Long convertToDatabaseColumn(Duration duration) {
    return duration != null ? duration.toMillis() : null;
  }

  @Override
  public Duration convertToEntityAttribute(Long dbData) {
    return dbData != null ? Duration.ofMillis(dbData) : null;
  }
}
