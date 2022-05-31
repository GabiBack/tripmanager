package com.example.tripmanager.email;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EmailSendgridConfig {

    @Value("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")
    private String key;

    @Bean
    public SendGrid getSendgrid() {
        return new SendGrid(key);
    }
}
