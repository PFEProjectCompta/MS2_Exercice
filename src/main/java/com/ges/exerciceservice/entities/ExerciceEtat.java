package com.ges.exerciceservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/
@Entity @NoArgsConstructor @AllArgsConstructor @Builder @Data
public class ExerciceEtat {
    @Id
    private String id;
    private boolean isFermer;
    private int classement; //size
    private double resultat;
    private String exerciceId;
}
