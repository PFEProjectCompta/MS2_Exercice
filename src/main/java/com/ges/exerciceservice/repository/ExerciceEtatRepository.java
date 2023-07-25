package com.ges.exerciceservice.repository;

import com.ges.exerciceservice.entities.ExerciceEtat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author WIAM
 **/
@RepositoryRestResource
public interface ExerciceEtatRepository extends JpaRepository<ExerciceEtat,String> {
}
