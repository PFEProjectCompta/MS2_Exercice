package com.ges.exerciceservice.repository;

import com.ges.exerciceservice.entities.SaisieJournaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SaisieJournalRepository extends JpaRepository<SaisieJournaux,String> {
}
