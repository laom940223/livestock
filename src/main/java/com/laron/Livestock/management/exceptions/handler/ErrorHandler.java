package com.laron.Livestock.management.exceptions.handler;


import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.AppResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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


    @ExceptionHandler(CustomFieldException.class )
    public ResponseEntity<AppResponse<?>> customField(CustomFieldException ex){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(

                AppResponse.builder()
                        .errors(
                                List.of(AppError.builder()
                                        .location(ex.getError().getLocation())
                                        .message(ex.getError().getMessage())
                                        .build())
                        )
                        .build()
        );
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse<?>> validationFailed(MethodArgumentNotValidException ex ){

        var errors = ex.getBindingResult().getFieldErrors().stream().map( fieldError -> AppError.builder()
                .message(fieldError.getDefaultMessage())
                .location(fieldError.getField())
                .build()).toList();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                AppResponse.builder()
                        .errors(errors)
                        .build()

        );

    }
}
