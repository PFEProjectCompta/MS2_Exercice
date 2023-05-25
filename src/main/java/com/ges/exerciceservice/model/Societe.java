package com.ges.exerciceservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Societe {
    private String id;
    private String raison_social;
    private String activite;
    private String adresse;
    private CompteUtilisateur compteUtilisateur;
}
