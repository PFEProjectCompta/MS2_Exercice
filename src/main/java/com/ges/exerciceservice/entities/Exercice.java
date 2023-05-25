package com.ges.exerciceservice.entities;

import com.ges.exerciceservice.model.Societe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "exercice")
public class Exercice {
    @Id
    private String id;
    private String date_debut;
    private String date_fin;
    @OneToMany(mappedBy = "exercice")
    private List<SaisieJournaux> saisieJournauxes;
    private String societeId;
    @Transient
    private Societe societe;
}
