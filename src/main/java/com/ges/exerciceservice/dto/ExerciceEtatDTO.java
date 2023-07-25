package com.ges.exerciceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/
@Data @NoArgsConstructor@AllArgsConstructor@Builder
public class ExerciceEtatDTO {
    private boolean isFermer;
    private int classement; //size
    private double resultat;
    private String exerciceId;
}
