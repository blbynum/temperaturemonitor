package com.bbynum.tempmon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertySourceConfiguration {


        @Bean
        @Profile("dev")
        public static PropertySourcesPlaceholderConfigurer developmentPropertyPlaceholderConfigurer() {
            String activeProfile = System.getProperty("spring.profiles.active", "dev");
            String propertiesFilename = "application-" + activeProfile + ".properties";

            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource(propertiesFilename));

            return configurer;
        }

        @Bean
        @Profile("prod")
        public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
            String activeProfile = System.getProperty("spring.profiles.active", "prod");
            String propertiesFilename = "application-" + activeProfile + ".properties";

            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource(propertiesFilename));

            return configurer;
        }
}
