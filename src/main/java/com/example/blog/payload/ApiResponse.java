package com.example.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean result;
    private Map<String, String> detailedMessage = null;

    public ApiResponse(String message, boolean result) {
        this.message = message;
        this.result = result;
    }
}
