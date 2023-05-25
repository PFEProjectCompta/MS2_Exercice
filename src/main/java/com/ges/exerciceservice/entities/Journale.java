package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String numCompte;
    private String numCompteTiere;
    private String libelle;
    private double credit;
    private double debit;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="saisie_journaux")
    private SaisieJournaux saisieJournaux;
}
