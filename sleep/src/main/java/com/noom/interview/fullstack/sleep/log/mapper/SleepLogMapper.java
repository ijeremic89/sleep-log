package com.noom.interview.fullstack.sleep.log.mapper;

import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SleepLogMapper {
  SleepLogMapper INSTANCE = Mappers.getMapper(SleepLogMapper.class);

  SleepLogEntity toEntity(SleepLogRequest request);

  @Mapping(
      target = "totalTimeInBedInSeconds",
      expression = "java(entity.getTotalTimeInBed().getSeconds())")
  SleepLogResponse toResponse(SleepLogEntity entity);
}
