package com.treinetic.interviews1.controller;

import com.treinetic.interviews1.dto.Login;
import com.treinetic.interviews1.dto.RegistrationStatus;
import com.treinetic.interviews1.dto.Student;
import com.treinetic.interviews1.repository.StudentRepository;
import com.treinetic.interviews1.service.AdminService;
import com.treinetic.interviews1.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentContoller {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private StudentRepository studentRepository;

    @Value( "${app.EXCHANGE}" )
    private String EXCHANGE;

    @Value( "${app.ROUTING_KEY}" )
    private String ROUTING_KEY;

    StudentService stdService = new StudentService(studentRepository);

    // get Not approved student list
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudent_Not_approved() {
        List<Student> listStd = stdService.getNot_approved();
        return new ResponseEntity<List<Student>>(listStd, HttpStatus.OK);
    }

    //save with notification about student registration
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registration(@RequestBody Student student) {
        student.getStatus("Not approved");
        String massage = stdService.saveStudent(student);
        RegistrationStatus regStatus = new RegistrationStatus(student, "PROCESS", "Student registration is success !!!!.....");
        template.convertAndSend(EXCHANGE, ROUTING_KEY, regStatus);
        return "Student registration is success !!!!.....";
    }

    //login
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean AdminLogin(@RequestBody Login login) {
        AdminService adminService = new AdminService();
        boolean message = adminService.LoginValidation(login);
        return message;
    }


    // update student
    @PutMapping("/Student/{eid}")
    public ResponseEntity<Student> updateTutorial(@PathVariable("eid") String eid, @RequestBody Student Student) {
        Optional<Student> studentData = studentRepository.findById(eid);
        if (studentData.isPresent()) {
            Student _Student = studentData.get();
            _Student.setFirst_name(Student.getFirst_name());
            _Student.setLast_name(Student.getLast_name());
            _Student.setPhone_number(Student.getPhone_number());
            _Student.setStatus(Student.getStatus());
            RegistrationStatus regStatus = new RegistrationStatus(_Student, "PROCESS", "Student approved by is admin !!!!.....");
            template.convertAndSend(EXCHANGE, ROUTING_KEY, regStatus);
            return new ResponseEntity<>(studentRepository.save(_Student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Student  login
    @GetMapping("/student{eid}")
    public boolean StudentLogin(@PathVariable("eid") String eid) {
        boolean message = stdService.LoginValidation(eid);
        return message;
    }
}
