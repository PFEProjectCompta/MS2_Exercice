package com.ges.exerciceservice.dto;

import com.ges.exerciceservice.enums.TypeJournal;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeJournauxDTO {
    private String code;
    private String intitule_journale;
    private TypeJournal type_journal;
    private String exerciceId;
}
