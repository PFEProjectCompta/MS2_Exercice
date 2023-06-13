package com.ges.exerciceservice.security;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;

@Configuration
public class FeignClientConfig  { //implements RequestInterceptor

//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
//            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//            String basicAuth = "Basic " + Base64Utils.encodeToString((token.getName() + ":" + token.getCredentials()).getBytes());
//            requestTemplate.header("Authorization", basicAuth);
//            System.out.println("hi : "+basicAuth);
//        }
//    }


//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }



}
