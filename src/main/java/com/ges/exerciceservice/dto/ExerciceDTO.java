package com.ges.exerciceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ExerciceDTO {
    private String date_debut;
    private String date_fin;
    private String societeId;
}
