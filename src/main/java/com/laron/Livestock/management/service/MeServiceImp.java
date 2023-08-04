package com.laron.Livestock.management.service;


import com.laron.Livestock.management.entities.UserEntity;
import com.laron.Livestock.management.exceptions.ResourceNotFound;
import com.laron.Livestock.management.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeServiceImp implements MeService{

    private final UserRepository userRepository;


    @Override
    public UserEntity getMe() {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            username =  ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }


        return userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFound("User was not found"));

    }
}
