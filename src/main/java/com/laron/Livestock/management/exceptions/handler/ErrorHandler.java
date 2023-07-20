package com.laron.Livestock.management.exceptions.handler;


import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResourceNotFound.class )
    public ResponseEntity<AppResponse<?>> resourceNotFound(ResourceNotFound ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(

                AppResponse.builder()
                        .errors(
                                List.of(AppError.builder()
                                        .location("REQUEST")
                                        .message(ex.getMessage())
                                        .build())
                        )
                        .build()
        );
    }
}
