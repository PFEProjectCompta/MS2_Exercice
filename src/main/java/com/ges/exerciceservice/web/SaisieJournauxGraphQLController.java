package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.SaisieJournalDTO;
import com.ges.exerciceservice.entities.SaisieJournaux;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class SaisieJournauxGraphQLController {

    @Autowired
    private SaisieJournalRepository saisieJournalRepository;
    @Autowired
    private ExerciceRepository exerciceRepository;

    @QueryMapping
    public List<SaisieJournaux> saisiJournauxList(){
        return saisieJournalRepository.findAll();
    }
    @QueryMapping
    public SaisieJournaux saisieJournalById(@Argument String id){
        return saisieJournalRepository.findById(id).get();
    }
    @MutationMapping
    public SaisieJournaux ajouterSaisieJournaux(@Argument SaisieJournalDTO saisiJournal){
        SaisieJournaux saisieJournaux=SaisieJournaux.builder()
                .id(UUID.randomUUID().toString())
                .position(saisiJournal.getPosition())
                .code(saisiJournal.getCode())
                .intitule_journale(saisiJournal.getIntitule_journale())
                .periode(saisiJournal.getPeriode())
                .exercice(exerciceRepository.findById(saisiJournal.getExerciceId()).get())
                .build();
        return saisieJournalRepository.save(saisieJournaux);
    }
    @MutationMapping
    public SaisieJournaux updateSaisieJournaux(@Argument SaisieJournalDTO saisiJournal,
                                               @Argument String id){
        SaisieJournaux saisieJournaux=saisieJournalRepository.findById(id).get();
        saisieJournaux.setCode(saisiJournal.getCode()==null? saisieJournaux.getCode() : saisiJournal.getCode());
        saisieJournaux.setPeriode(saisiJournal.getPeriode()==null? saisieJournaux.getPeriode():saisiJournal.getPeriode());
        saisieJournaux.setPosition(saisiJournal.getPosition()==null? saisieJournaux.getPosition():saisiJournal.getPosition());
        saisieJournaux.setIntitule_journale(saisiJournal.getIntitule_journale()==null? saisieJournaux.getIntitule_journale():saisiJournal.getIntitule_journale());
        saisieJournaux.setExercice(saisiJournal.getExerciceId()==null?saisieJournaux.getExercice() :exerciceRepository.findById(saisiJournal.getExerciceId()).get());
        return saisieJournalRepository.save(saisieJournaux);
    }
    @MutationMapping
    public SaisieJournaux deleteSaisieJournaux(@Argument String id){
        SaisieJournaux saisieJournaux=saisieJournalRepository.findById(id).get();
        saisieJournalRepository.delete(saisieJournalRepository.findById(id).get());
        return saisieJournaux ;
    }
}
