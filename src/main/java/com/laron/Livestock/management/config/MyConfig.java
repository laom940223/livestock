package com.laron.Livestock.management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MyConfig {

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationProvider authProvider;
    private final AuthenticationFilter filter;
    private final CorsConfigurationSource corsConfigurationSource;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {




        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
//                .exceptionHandling( ex->{
//                                        ex.authenticationEntryPoint(authenticationEntryPoint);
//
//                })
                .authorizeHttpRequests(auth->{

                            auth.requestMatchers("/error").permitAll();
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.requestMatchers("/api/farms").hasAuthority("OWNER");
                            auth.requestMatchers("/api/admin/**").hasAuthority("ADMIN");
                            auth.anyRequest().authenticated();



                        }
                )

                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)

//                .exceptionHandling( exception -> exception.accessDenedHandler(accessDeniedHandler))
                .build();

    }




}
