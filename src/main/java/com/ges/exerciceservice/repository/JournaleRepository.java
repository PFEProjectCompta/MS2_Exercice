package com.ges.exerciceservice.repository;

import com.ges.exerciceservice.entities.Journale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JournaleRepository extends JpaRepository<Journale,String> {
}
