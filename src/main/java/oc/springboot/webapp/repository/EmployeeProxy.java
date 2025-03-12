package oc.springboot.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

    public Employee getEmployee(final long id) {
        String baseApiURL = props.getApiURL();
        String getEmployeesURL = baseApiURL + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeesURL,
                HttpMethod.GET,
                null,
                Employee.class);

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

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

    public Employee createEmployee(Employee employee) {
        String baseApiURL = props.getApiURL();
        String createEmployeeURL = baseApiURL + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeURL,
                HttpMethod.POST,
                requestEntity,
                Employee.class);

        log.debug("Create Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee updateEmployee(Employee employee) {
        String baseApiURL = props.getApiURL();
        String updateEmployeeURL = baseApiURL + "/employee/" + employee.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeURL,
                HttpMethod.PUT,
                requestEntity,
                Employee.class);

        log.debug("Update Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteEmployee(int id) {
        String baseApiUrl = props.getApiURL();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Employee call " + response.getStatusCode().toString());
    }

}