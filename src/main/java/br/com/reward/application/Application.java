/**
 * @author filipe.pinheiro, 29/09/2018
 */
package br.com.reward.application;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"br.com.reward"})
@EnableJpaRepositories(basePackages={"br.com.reward"}) 
@EntityScan(basePackages={"br.com.reward"})
public class Application {

	@Value(value = "${spring.jpa.properties.hibernate.jdbc.time_zone:UTC}")
	private String timeZone;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @PostConstruct
    void started() {
		// Set JVM timezone as UTC
		// Remember to also set:
		// 1: useLegacyDatetimeCode=false in the URL of the underline database. Its syntax might depend on the database.
		// 2: spring.jpa.properties.hibernate.jdbc.time_zone
        TimeZone.setDefault(
			TimeZone.getTimeZone(
				timeZone
			)
		);
	}
	
	

}
