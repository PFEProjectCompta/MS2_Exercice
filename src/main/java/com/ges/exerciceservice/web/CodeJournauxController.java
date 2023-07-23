package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.CodeJournauxDTO;
import com.ges.exerciceservice.dto.ExerciceDTO;
import com.ges.exerciceservice.entities.CodeJournaux;
import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.repository.CodeJournauxRepository;
import com.ges.exerciceservice.repository.ExerciceRepository;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author WIAM
 **/

@Controller
public class CodeJournauxController {
    @Autowired
    private CodeJournauxRepository codeJournauxRepository;
    @Autowired
    private ExerciceRepository exerciceRepository;

    @QueryMapping
    public List<CodeJournaux> fullCodeJournalListByExerciceId(@Argument String id){
        List<CodeJournaux> codeJournauxes=codeJournauxRepository.findAll();
        List<CodeJournaux> codeJournauxesListByCodeJournalId=new ArrayList<>();
        for(int i=0;i<codeJournauxes.size();i++){
            if(codeJournauxes.get(i).getExercice().getId().equals(id)){
                codeJournauxesListByCodeJournalId.add(codeJournauxes.get(i));
            }
        }
        return codeJournauxesListByCodeJournalId;
    }
    @QueryMapping
    public CodeJournaux codeJournalById(@Argument String id){
        CodeJournaux codeJournaux=codeJournauxRepository.findById(id).get();
        return codeJournaux;
    }

    @MutationMapping
    public CodeJournaux ajouterCodeJournal(@Argument CodeJournauxDTO codeJournauxDTO){
        CodeJournaux codeJournaux=new CodeJournaux();
        codeJournaux.setId(UUID.randomUUID().toString());
        codeJournaux.setCode(codeJournauxDTO.getCode());
        codeJournaux.setIntitule_journale(codeJournauxDTO.getIntitule_journale());
        codeJournaux.setType_journal(codeJournauxDTO.getType_journal());
        codeJournaux.setExercice(exerciceRepository.findById(codeJournauxDTO.getExerciceId()).get());
        codeJournauxRepository.save(codeJournaux);
        return codeJournaux;
    }
    @MutationMapping
    public CodeJournaux modifierCodeJournal(@Argument CodeJournauxDTO codeJournauxDTO, @Argument String id){
        CodeJournaux codeJournaux=codeJournauxRepository.findById(id).get();
        codeJournaux.setCode(codeJournauxDTO.getCode());
        codeJournaux.setIntitule_journale(codeJournauxDTO.getIntitule_journale());
        codeJournaux.setType_journal(codeJournauxDTO.getType_journal());
        codeJournaux.setExercice(exerciceRepository.findById(codeJournauxDTO.getExerciceId()).get());
        codeJournauxRepository.save(codeJournaux);
        return codeJournaux;
    }
    @MutationMapping
    public CodeJournaux supprimerCodeJournal(@Argument String id){
        CodeJournaux codeJournaux=codeJournauxRepository.findById(id).get();
        codeJournauxRepository.delete(codeJournaux);
        return codeJournaux;
    }
}
