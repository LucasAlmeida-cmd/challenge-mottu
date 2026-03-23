package com.example.challenge_mottu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChallengeMottuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMottuApplication.class, args);
	}

}
