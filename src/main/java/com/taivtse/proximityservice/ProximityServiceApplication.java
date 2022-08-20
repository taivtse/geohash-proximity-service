package com.taivtse.proximityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProximityServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProximityServiceApplication.class, args);
  }

}
