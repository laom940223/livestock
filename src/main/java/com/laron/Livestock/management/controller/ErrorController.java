package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.exceptions.CustomFieldException;
import com.laron.Livestock.management.exceptions.MyAccessDeniedException;
import com.laron.Livestock.management.utils.AppError;
import com.laron.Livestock.management.utils.AppResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ErrorController  implements org.springframework.boot.web.servlet.error.ErrorController {



    private static final String PATH = "error";


    private boolean debug = true;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(PATH)
    public ResponseEntity<AppResponse<?>> error(WebRequest request, HttpServletResponse response) {
        var error= getErrorAttributes(request, true);


        var exception = (String) error.get("exception");

        if(exception.contains("ExpiredJwt")){
             throw  new MyAccessDeniedException("JWT has expired");
        }


        if(exception.contains("BadCredentials")){

                throw  new CustomFieldException("Bad credentials", new AppError("password", "Password is incorrect"));
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                AppResponse.builder()
                        .errors(List.of(new AppError("SERVER","Something went wrong")))
                        .build()

        );
    }

    private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults()
                .including(ErrorAttributeOptions.Include.MESSAGE)
                .including(ErrorAttributeOptions.Include.EXCEPTION)
                .including(ErrorAttributeOptions.Include.BINDING_ERRORS);
        if(includeStackTrace){
            options = options.including(ErrorAttributeOptions.Include.STACK_TRACE);
        }



        return this.errorAttributes.getErrorAttributes(request, options);
    }


}
