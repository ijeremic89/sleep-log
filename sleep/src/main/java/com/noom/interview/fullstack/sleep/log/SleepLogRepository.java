package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface SleepLogRepository extends CrudRepository<SleepLogEntity, Long> {}
