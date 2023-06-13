package com.ges.exerciceservice.model;

import com.ges.exerciceservice.enums.NatureCompte;
import lombok.Data;

@Data
public class CompteGeneral {
    private String id;
    private NatureCompte natureCompte;
    private String debutFaurchette;
    private String finFaurchette;
}
