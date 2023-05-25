package com.ges.exerciceservice.web;

import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.model.CompteUtilisateur;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import com.ges.exerciceservice.service.SocieteRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciceController {

    private ExerciceRepository exerciceRepository;
    private SaisieJournalRepository saisieJournalRepository;
    private SocieteRestClientService societeRestClientService;

    public ExerciceController(ExerciceRepository exerciceRepository, SaisieJournalRepository saisieJournalRepository, SocieteRestClientService societeRestClientService) {
        this.exerciceRepository = exerciceRepository;
        this.saisieJournalRepository = saisieJournalRepository;
        this.societeRestClientService = societeRestClientService;
    }
    @GetMapping("fullExercice/{id}")
    public Exercice getFullExercice(@PathVariable String id){
        Exercice exercice=exerciceRepository.findById(id).get();
        exercice.setSociete(societeRestClientService.SocieteById(exercice.getSocieteId()));
        return exercice;
    }
    @GetMapping("fullExercieSocieteCompteUtilisateur/{idSociete}")
    public CompteUtilisateur fullSocieteCompteUtilisateur(@PathVariable String idSociete){
        return societeRestClientService.fullSocieteCompteUtilisateur(idSociete);
    }
}
