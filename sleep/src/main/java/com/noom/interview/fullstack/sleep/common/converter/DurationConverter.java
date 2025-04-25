package com.noom.interview.fullstack.sleep.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter
public class DurationConverter implements AttributeConverter<Duration, Long> {

  @Override
  public Long convertToDatabaseColumn(Duration duration) {
    if (duration == null) {
      return null;
    }
    return duration.getSeconds(); // Store as seconds
  }

  @Override
  public Duration convertToEntityAttribute(Long dbData) {
    if (dbData == null) {
      return null;
    }
    return Duration.ofSeconds(dbData); // Convert back to Duration
  }
}
