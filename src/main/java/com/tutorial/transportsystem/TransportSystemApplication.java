package com.tutorial.transportsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TransportSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransportSystemApplication.class, args);
  }
}
