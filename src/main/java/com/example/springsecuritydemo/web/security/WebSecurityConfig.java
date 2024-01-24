package com.example.springsecuritydemo.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Autowired
        private UserDetailsService uds;

        @Autowired
        private BCryptPasswordEncoder encoder;

       
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers("/", "/home", "/register", "/saveUser").permitAll()
                                             
                                                .requestMatchers("/admin").hasAuthority("ADMIN")
                                                .requestMatchers("/moderator").hasAuthority("MODERATOR")
                                                .requestMatchers("/user").hasAuthority("USER")
                                                .requestMatchers("/common").hasAnyAuthority("USER", "MODERATOR", "Admin")
                                                .anyRequest().authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .permitAll()
                                                // .defaultSuccessUrl("/welcome",true)
                                                .successHandler(myAuthenticationSuccessHandler()))
                                .logout((logout) -> logout.permitAll())
                                .exceptionHandling((exceptionHandling) -> exceptionHandling
                                                .accessDeniedPage("/access-denied"))
                                .authenticationProvider(authenticationProvider());

                return http.build();
        }

       
        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(uds);
                authenticationProvider.setPasswordEncoder(encoder);
                return authenticationProvider;
        }

        @Bean
        public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
                return new MySimpleUrlAuthenticationSuccessHandler();
        }
       
}
