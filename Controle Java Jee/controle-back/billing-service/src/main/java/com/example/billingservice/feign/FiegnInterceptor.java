package com.example.billingservice.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FiegnInterceptor implements RequestInterceptor {

    public String accesToken(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        String accessToken = session.getTokenString();
        return accessToken;
    }
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization","Bearer "+ accesToken());
    }
}
