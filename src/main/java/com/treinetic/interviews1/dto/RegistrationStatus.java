package com.treinetic.interviews1.dto;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RegistrationStatus implements Serializable {

    private Student student;
    private String status;
    private String message;

    public RegistrationStatus() {
    }

    public RegistrationStatus(Student student, String status, String message) {
        this.student = student;
        this.status = status;
        this.message = message;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RegistrationStatus{" +
                "student=" + student +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
