package local.home.daytoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "local.home.daytoday.dao")
@EntityScan(value = "local.home.daytoday.model")
public class DayToDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayToDayApplication.class, args);
	}

}