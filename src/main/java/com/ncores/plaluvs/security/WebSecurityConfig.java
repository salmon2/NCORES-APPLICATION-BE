package com.ncores.plaluvs.security;

import com.ncores.plaluvs.security.jwt.JwtAuthenticationFilter;
import com.ncores.plaluvs.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;

        http.headers().frameOptions().disable();
        http.authorizeRequests();

        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/images/**").permitAll()
                // ?????? ?????? ?????? API ????????? login ?????? ??????
                .antMatchers("/images/**").permitAll()
                // css ????????? login ?????? ??????
                .antMatchers("/css/**").permitAll()
                // ?????? ?????? ?????? API ????????? login ?????? ??????
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/user/kakao/callback").permitAll()
                .antMatchers("/**").permitAll()
                // ??? ??? ?????? ???????????? '??????'
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
