package com.noom.interview.fullstack.sleep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sleep-log")
public class SleepLogController {

  @GetMapping
  public String test() {
    return "Hello Java!";
  }
}
