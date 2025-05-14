package com.example.tutorial.demo.configuration;

import com.example.tutorial.demo.service.UserDetailServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JournalConfiguration {
    @Autowired
    private UserDetailServiceimpl userDetailServiceimpl;
    @Bean
    SecurityFilterChain defaultFilterChan(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests((req)->{req.requestMatchers("/journal/**","/user/**").authenticated().anyRequest().permitAll();});
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable());;

        return  http.build();
    }
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceimpl);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
