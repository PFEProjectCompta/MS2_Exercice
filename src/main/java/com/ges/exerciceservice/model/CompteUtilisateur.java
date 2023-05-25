package com.ges.exerciceservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CompteUtilisateur {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;
    private Date date_naissance;
    private boolean actif;
    private Bureau bureau;
    private List<Societe> societes;
}
