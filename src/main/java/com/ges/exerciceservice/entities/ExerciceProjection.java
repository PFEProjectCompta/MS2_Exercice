package com.ges.exerciceservice.entities;

import com.ges.exerciceservice.model.Societe;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "fullExercice", types = Exercice.class)
public interface ExerciceProjection {
    public String getId();
    public Societe getSociete();
    public String setDate_debut();
    public String setDate_fin();
    public String setSocieteId();
    public List<SaisieJournaux> getSaisieJournauxes();
}
