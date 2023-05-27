package com.ges.exerciceservice.dto;
import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SaisieJournalDTO {
    private State position;
    private String periode;
    private String code;
    private String intitule_journale;
    private String exerciceId;

}
