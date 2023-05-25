package com.ges.exerciceservice.entities;

import com.ges.exerciceservice.model.Societe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "exercice")
public class Exercice {
    @Id
    private String id;
    private Date date_debut;
    private Date date_fin;
    @OneToMany(mappedBy = "exercice")
    private List<SaisieJournaux> saisieJournauxes;
    private String societeId;
    @Transient
    private Societe societe;
}
