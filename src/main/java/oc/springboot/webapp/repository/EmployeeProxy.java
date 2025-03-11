package oc.springboot.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import oc.springboot.webapp.configuration.CustomProperties;
import oc.springboot.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * 
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        String baseApiURL = props.getApiURL();
        String getEmployeesURL = baseApiURL + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                });

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

}