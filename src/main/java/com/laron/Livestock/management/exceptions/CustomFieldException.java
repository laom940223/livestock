package com.laron.Livestock.management.exceptions;

import com.laron.Livestock.management.utils.AppError;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class CustomFieldException extends RuntimeException{

    private AppError error;


    public CustomFieldException (String message, AppError error){

        super(message);
        this.error = error;

            }

}
