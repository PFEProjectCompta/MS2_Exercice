package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.ExerciceDTO;
import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.entities.Journale;
import com.ges.exerciceservice.entities.SaisieJournaux;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return exerciceRepository.findAll();
    }

    @QueryMapping
    public List<Exercice> fullExerciceList(){
        List<Exercice> exercices=exerciceRepository.findAll();
        exercices.forEach(exercice -> {
            exercice.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),exercice.getSocieteId()));
        });
        exercices.forEach(exercice -> {
            exercice.getSociete().setCompteUtilisateur(societeRestClientService.fullSocieteCompteUtilisateur(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),exercice.getSocieteId()));
        });
        return exerciceRepository.findAll();
    }

    @QueryMapping
    public List<Exercice> fullExerciceListBySocieteId(@Argument String id){
        List<Exercice> exercices=exerciceRepository.findAll();
        List<Exercice> exerciceListBySocieteId=new ArrayList<>();
        for(int i=0;i<exercices.size();i++){
            if(exercices.get(i).getSocieteId().equals(id)){
                exerciceListBySocieteId.add(exercices.get(i));
            }
        }
        return exerciceListBySocieteId;
    }

    @QueryMapping
    public Exercice exerciceById(@Argument String id){
        Exercice exercice=exerciceRepository.findById(id).get();
//        exercice.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),exercice.getSocieteId()));
//        exercice.getSociete().setCompteUtilisateur(societeRestClientService.fullSocieteCompteUtilisateur(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),exercice.getSocieteId()));
        return exercice;
    }
    @MutationMapping
    public Exercice ajouterExercice(@Argument ExerciceDTO exercice){
        Exercice exerciceAjout=new Exercice();
        exerciceAjout.setId(UUID.randomUUID().toString());
        exerciceAjout.setDate_debut(exercice.getDate_debut());
        exerciceAjout.setDate_fin(exercice.getDate_fin());
        exerciceAjout.setSocieteId(exercice.getSocieteId());
        return exerciceRepository.save(exerciceAjout);
    }

    @MutationMapping
    public Exercice modifierExercice(@Argument ExerciceDTO exercice, @Argument String id){
        Exercice exerciceModifier=exerciceRepository.findById(id).get();
        exerciceModifier.setDate_debut(exercice.getDate_debut());
        exerciceModifier.setDate_fin(exercice.getDate_fin());
        exerciceModifier.setSocieteId(exercice.getSocieteId());
        return exerciceRepository.save(exerciceModifier);
    }
}
