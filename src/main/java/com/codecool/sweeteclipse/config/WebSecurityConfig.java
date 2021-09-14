package com.codecool.sweeteclipse.config;

import com.codecool.sweeteclipse.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplemented userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImplemented userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        int strength = 10;  // 2^strength iterations (supposedly, the nr. of XOR filters in the algorithm)
                            // (10 is sufficiently hard for current hardware to brute-force)

        return new BCryptPasswordEncoder(strength, new SecureRandom()); // use a cryptographically secure random nr.
                                                                        // as the salt

            // for testing purposes (use no encoding)
        // return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/login").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling();


    }



}
