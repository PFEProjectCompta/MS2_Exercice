package com.ges.exerciceservice.repository;

import com.ges.exerciceservice.entities.CodeJournaux;
import com.ges.exerciceservice.entities.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author WIAM
 **/
@RepositoryRestResource
public interface CodeJournauxRepository extends JpaRepository<CodeJournaux,String> {
}
