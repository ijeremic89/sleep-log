package com.noom.interview.fullstack.sleep.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(String message, Map<String, String> errors, LocalDateTime timestamp) {}
