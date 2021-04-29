package com.treinetic.interviews1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {
    @Value("${app.is_maintain}")
    private String isMaintain;

    public boolean isServerHealthy() {
        System.out.println(isMaintain);
        if (isMaintain.equals("yes")) {
            return true;
        }
        return false;
    }
}
