package com.example.tripmanager.email;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EmailSendgridConfig {

    @Value("${sendgrid.key}")
    private String key;

    @Bean
    public SendGrid getSendgrid() {
        return new SendGrid(key);
    }
}
