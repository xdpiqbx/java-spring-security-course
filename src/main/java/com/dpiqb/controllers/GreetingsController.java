package com.dpiqb.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {
  @GetMapping
  public ResponseEntity<String> sayHallo(){
    return ResponseEntity.ok("Hello from our API");
  }

  @GetMapping("/say-good-bye")
  public ResponseEntity<String> sayBye(){
    return ResponseEntity.ok("Good bye from API");
  }
}
