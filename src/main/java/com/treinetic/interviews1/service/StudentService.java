package com.treinetic.interviews1.service;
import com.treinetic.interviews1.dto.Login;
import com.treinetic.interviews1.dto.Student;
import com.treinetic.interviews1.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public String saveStudent(Student student){
        studentRepository.save(student);
        return "Save";
    }

    public List<Student> getNot_approved(){
        return studentRepository.findNot_approved("Not_approved");
    }


    public boolean LoginValidation(String eid) {
        Optional<Student> student = studentRepository.findById(eid);
        Student _Student = student.get();

        if (student.isPresent() && _Student.getStatus() == "Approved") {
            return true;
        }
        return false;
    }
}
