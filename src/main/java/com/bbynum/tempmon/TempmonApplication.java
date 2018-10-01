package com.bbynum.tempmon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class TempmonApplication {

    @Autowired
    ConfigurableEnvironment environment;

	private static final Logger log = LogManager.getLogger(TempmonApplication.class);

	public static void main(String[] args) {
		System.setProperty("spring.profiles.default", "prod");
        File file = new File("./log4j2.properties");

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(file.toURI());
	    SpringApplication.run(TempmonApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (String... args) -> environment.getPropertySources().forEach(property -> {
			String name = property.getName();

			Matcher matcher = Pattern.compile("classpath:/(application.*\\.properties)").matcher(name);
			boolean found = matcher.find();

			if (found) {
				log.info("DataServiceApplication:: using properties file -> {}", matcher.group(1));
			}
		});
	}
}
