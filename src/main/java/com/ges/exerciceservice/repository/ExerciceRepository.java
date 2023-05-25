package com.ges.exerciceservice.repository;

import com.ges.exerciceservice.entities.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExerciceRepository extends JpaRepository<Exercice,String> {
}
