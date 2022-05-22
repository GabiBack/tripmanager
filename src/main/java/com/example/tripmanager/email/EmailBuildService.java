package com.example.tripmanager.email;

import org.springframework.stereotype.Service;

@Service
public class EmailBuildService {

    public String buildEmail(String name, String link) {
        return "<a href=" + link + ">Zaloguj się " + name +  "  " + link + "</a>";
    }
}
