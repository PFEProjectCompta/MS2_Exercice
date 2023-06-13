package com.ges.exerciceservice.model;

import lombok.Data;

@Data
public class PlanComptableElement {
    private String id;
    private String numeroCompte;
    private String intitule;
    private boolean reporter;
    private CompteGeneral compteGeneral;
}
