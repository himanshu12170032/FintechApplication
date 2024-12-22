package com.assignment.project.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ErrorDetail {
    private String error;
    private String message;
    private LocalDateTime timeStamp;

    public ErrorDetail(String error, String message, LocalDateTime timeStamp) {
        this.error = error;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
