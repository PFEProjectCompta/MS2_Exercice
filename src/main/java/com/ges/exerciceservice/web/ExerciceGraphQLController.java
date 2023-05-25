package com.ges.exerciceservice.web;

import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExerciceGraphQLController {
    private ExerciceRepository exerciceRepository;
    private SocieteRestClientService societeRestClientService;
    public ExerciceGraphQLController(ExerciceRepository exerciceRepository, SocieteRestClientService societeRestClientService) {
        this.exerciceRepository = exerciceRepository;
        this.societeRestClientService = societeRestClientService;
    }

    @QueryMapping
    public List<Exercice> exerciceList(){
        List<Exercice> exercices=exerciceRepository.findAll();
        exercices.forEach(exercice -> {
            exercice.setSociete(societeRestClientService.SocieteById(exercice.getSocieteId()));
        });
        return exerciceRepository.findAll();
    }

    @QueryMapping
    public List<Exercice> fullExerciceList(){
        List<Exercice> exercices=exerciceRepository.findAll();
        exercices.forEach(exercice -> {
            exercice.setSociete(societeRestClientService.SocieteById(exercice.getSocieteId()));
        });
        exercices.forEach(exercice -> {
            exercice.getSociete().setCompteUtilisateur(societeRestClientService.fullSocieteCompteUtilisateur(exercice.getSocieteId()));
        });

        return exerciceRepository.findAll();
    }
}
