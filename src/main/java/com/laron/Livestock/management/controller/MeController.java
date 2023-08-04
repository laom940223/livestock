package com.laron.Livestock.management.controller;


import com.laron.Livestock.management.repo.UserRepository;
import com.laron.Livestock.management.service.MeService;
import com.laron.Livestock.management.utils.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/api/me")
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin(origins = "http://localhost:5173")
public class MeController {



    private final MeService meService;



    @GetMapping
    public ResponseEntity<AppResponse<?>> getMyInfo(HttpServletRequest request){

//        log.info(request.getHeader("Authorization"));


        return  ResponseEntity.ok(

                AppResponse.builder()
                        .data( meService.getMe())
                        .build()
        );



    }


}
