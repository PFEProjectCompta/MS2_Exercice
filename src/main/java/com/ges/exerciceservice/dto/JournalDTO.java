package com.ges.exerciceservice.dto;
import com.ges.exerciceservice.entities.SaisieJournaux;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class JournalDTO {
    private int jour;
    private String numFacture;
    private String ref;
    private String numCompteId;
    private String numCompteTiereId;
    private String libelle;
    private double credit;
    private double debit;
    private String saisieJournauxId;
}
