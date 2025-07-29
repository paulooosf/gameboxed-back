package io.github.paulooosf.gameboxed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GameboxedApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameboxedApplication.class, args);
	}

}
