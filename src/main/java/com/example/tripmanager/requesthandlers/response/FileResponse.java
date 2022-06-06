package com.example.tripmanager.requesthandlers.response;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FileResponse {

    private String name;
    private String url;
    private String type;
    private long size;
}
