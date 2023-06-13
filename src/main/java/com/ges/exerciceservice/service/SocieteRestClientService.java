package com.ges.exerciceservice.service;

import com.ges.exerciceservice.model.Bureau;
import com.ges.exerciceservice.model.CompteUtilisateur;
import com.ges.exerciceservice.model.Societe;
import com.ges.exerciceservice.security.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "office-service")
public interface SocieteRestClientService {
    @GetMapping(value = "/societes/{id}?projection=societeProjection" )
    public Societe SocieteById(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable String id);
    @GetMapping("/societes?projection=societeProjection")
    public PagedModel<Societe> allSocietes(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
    @GetMapping("/fullSocieteCompteUtilisateur/{id}")
    public CompteUtilisateur fullSocieteCompteUtilisateur(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable String id);
    @GetMapping("/Fullbureau/{id}")
    public Bureau fullExercieSocieteCompteBureauAdmine(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable String id);
}
