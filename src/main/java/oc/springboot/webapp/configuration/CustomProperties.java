package oc.springboot.webapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "oc.springboot.webapp")
public class CustomProperties {

    @Autowired
    private String apiURL;

}
