package com.sagar.taskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        disable csrf token
//        http.csrf(customizer -> customizer.disable());
//
////        enable authentication
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//
////        add spring boot build in login form
////        http.formLogin(Customizer.withDefaults());
//
////        for enabling login through postman and other tool
//        http.httpBasic(Customizer.withDefaults());
//
////        make http request stateless --> without csrf token
////        every time we need to pass username and password
////        not working for browser login page ==> spring boot default login form
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();


//        this means before userauthenticationfilter do jwt filter
//        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register", "/api/login")
                        .permitAll() // Allow register endpoint without authentication
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

//        we have to create DaoAuthenticationProvider obj and set password encoder,
//        userdetailservice and return provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // password encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        // we need to create one class that implements UserDetailsService interface here it is: MyUserDetailService class
        // using dependency injection its object get created
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("sagar")
//                .password("sagar")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("sagar1")
//                .password("sagar1")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
