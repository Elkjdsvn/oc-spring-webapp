package oc.springboot.webapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "oc.springboot.webapp")
@Data
public class CustomProperties {

    private String apiURL;

}
