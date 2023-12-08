package com.filmland.assestment.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class DefaultResponseMessage {

    private String status;
    private String message;

    // Static method to create a response
    public static DefaultResponseMessage create(String status, String message) {
        DefaultResponseMessage defaultResponseMessage = new DefaultResponseMessage();
        defaultResponseMessage.setStatus(status);
        defaultResponseMessage.setMessage(message);
        return defaultResponseMessage;
    }
}
