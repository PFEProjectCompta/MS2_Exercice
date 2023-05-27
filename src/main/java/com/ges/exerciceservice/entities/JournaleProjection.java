package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "jounaleProjection" ,types = Journale.class)
public interface JournaleProjection {
    public String getId();
    public int getJour();
    public String getNumFacture();
    public String getRef();
    public String getNumCompte();
    public String getNumCompteTiere();
    public String getLibelle();
    public double getCredit();
    public double getDebit();
    public SaisieJournaux getSaisieJournaux();
}
