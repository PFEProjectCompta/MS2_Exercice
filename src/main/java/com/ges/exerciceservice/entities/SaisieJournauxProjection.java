package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.exerciceservice.enums.State;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "saisieJournauxProjection" , types = SaisieJournaux.class)
public interface SaisieJournauxProjection {
    public String getId();
    public String getPosition();
    public String getPeriode();
    public String getCode();
    public String getIntitule_journale();
    public Exercice getExercice();
    public List<Journale> getJournales();
}
