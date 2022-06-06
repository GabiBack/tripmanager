package com.example.tripmanager.requesthandlers.response;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

    private String message;

}
