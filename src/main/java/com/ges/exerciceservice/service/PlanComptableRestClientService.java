package com.ges.exerciceservice.service;

import com.ges.exerciceservice.model.PlanComptableElement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "plan-comptable-service")
public interface PlanComptableRestClientService {
    @GetMapping("/planComptableElements/{id}?projection=planComptableElementProjection")
    public PlanComptableElement planComptableElementById(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable String id);
    @GetMapping("/planComptableElements?projection=planComptableElementProjection")
    public PagedModel<PlanComptableElement> allplanComptableElements(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
