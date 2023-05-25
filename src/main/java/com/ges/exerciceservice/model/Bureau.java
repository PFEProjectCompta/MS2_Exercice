package com.ges.exerciceservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class Bureau {

    private String id;
    private String nom;
    private String adresse;
    private String ville;
    private String paye;
    private String numero_tele;
    private String email;
    private List<CompteUtilisateur> compteUtilisateurs;
    private AdmineBureau admine;
}
