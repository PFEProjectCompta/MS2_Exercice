package com.ges.exerciceservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ges.exerciceservice.enums.TypeJournal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author WIAM
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "code_journaux")
public class CodeJournaux {
    @Id
    private String id;
    private String code;
    private String intitule_journale;
    private TypeJournal type_journal;
    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "exercice")
    private Exercice exercice;
    @OneToMany(mappedBy = "code_Journal",cascade = CascadeType.REMOVE)
    private List<SaisieJournaux> saisieJournaux;
}
