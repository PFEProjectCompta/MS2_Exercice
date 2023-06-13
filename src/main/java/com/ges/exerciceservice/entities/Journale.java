package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.exerciceservice.model.PlanComptableElement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "journale")
public class Journale {
    @Id
    private String id;
    private int jour;
    private String numFacture;
    private String ref;
    private String numCompteId;
    private String numCompteTiereId;
    private String libelle;
    private double credit;
    private double debit;
    @Transient
    private PlanComptableElement numCompte;
    @Transient
    private PlanComptableElement numCompteTiere;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="saisie_journaux")
    private SaisieJournaux saisieJournaux;
}
