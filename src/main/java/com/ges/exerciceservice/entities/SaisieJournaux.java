package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.exerciceservice.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "saisie_de_journaux")
public class SaisieJournaux {
    @Id
    private String id;
    private State position;
    private String periode;
    private String code;
    private String intitule_journale;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "exercice")
    private Exercice exercice;
    @OneToMany(mappedBy = "saisieJournaux",cascade = CascadeType.REMOVE)
    private List<Journale> journales;
}
