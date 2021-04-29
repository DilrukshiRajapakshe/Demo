package com.treinetic.interviews1.service;

import com.treinetic.interviews1.dto.Login;
import com.treinetic.interviews1.dto.RegistrationStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;

public class AdminService {

    @Value("${app.email}" )
    private String email;
    @Value("${app.password}" )
    private  String password;

    public boolean LoginValidation(Login login) {
        if(email.equals(login.getEmail()) && password.equals(login.getPassword()))
            return true;
        return false;
    }


}
