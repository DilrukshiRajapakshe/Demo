package com.treinetic.interviews1.controller;

import com.treinetic.interviews1.dto.Login;
import com.treinetic.interviews1.dto.RegistrationStatus;
import com.treinetic.interviews1.dto.Student;
import com.treinetic.interviews1.repository.StudentRepository;
import com.treinetic.interviews1.service.AdminService;
import com.treinetic.interviews1.service.HealthCheckService;
import com.treinetic.interviews1.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
public class HealthCheckController {

    private HealthCheckService healthCheckService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostConstruct
    public void createInstance() {
        this.healthCheckService = new HealthCheckService();
    }

}
