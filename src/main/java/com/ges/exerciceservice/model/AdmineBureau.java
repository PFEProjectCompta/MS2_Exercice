package com.ges.exerciceservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class AdmineBureau {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private List<Bureau> bureaus;
}
