package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.ExerciceEtatDTO;
import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.entities.ExerciceEtat;
import com.ges.exerciceservice.repository.ExerciceEtatRepository;
import com.ges.exerciceservice.repository.ExerciceRepository;
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
public class ExerciceEtatGraphQLController {

    @Autowired
    private ExerciceEtatRepository exerciceEtatRepository;
    @Autowired
    private ExerciceRepository exerciceRepository;
    @QueryMapping
    public List<ExerciceEtat> exerciceEtatList(){
        return exerciceEtatRepository.findAll();
    }
    @QueryMapping
    public ExerciceEtat findByIdExercice(@Argument String id){
        List<ExerciceEtat> exerciceEtats=exerciceEtatRepository.findAll();
        for (int i=0;i<exerciceEtats.size();i++){
            if(exerciceEtats.get(i).getExerciceId().equals(id)){
                return exerciceEtats.get(i);
            }
        }
        return null;
    }

    @QueryMapping
    public boolean idAllExerciceCloseOfSociete(@Argument String idSociete){
        List<Exercice>  exercices=exerciceRepository.findAll();
        for (int i=0;i<exercices.size();i++){
            if(!findByIdExercice(exercices.get(i).getId()).isFermer()){
                return false;
            }
        }
        return true;
    }
    @QueryMapping
    public ExerciceEtat getLastResultat(@Argument String idSociete){
        List<ExerciceEtat>  exerciceEtats=exerciceEtatRepository.findAll();
        return exerciceEtats.get(exerciceEtats.size()-2);
    }
    @MutationMapping
    public ExerciceEtat ajouterExerciceEtat(@Argument String exerciceId){
        List<ExerciceEtat> exerciceEtats=exerciceEtatRepository.findAll();
        ExerciceEtat exerciceEtat=ExerciceEtat.builder()
                .id(UUID.randomUUID().toString())
                .isFermer(false)
                .classement(exerciceEtats.size())
                .resultat(0)
                .exerciceId(exerciceId)
                .build();
        return exerciceEtatRepository.save(exerciceEtat);
    }
    @MutationMapping
    public ExerciceEtat modifierExerciceEtatResultat(@Argument String idExercice,@Argument double resultat){
        ExerciceEtat exerciceEtat=findByIdExercice(idExercice);
        exerciceEtat.setFermer(exerciceEtat.isFermer());
        exerciceEtat.setClassement(exerciceEtat.getClassement());
        exerciceEtat.setResultat(resultat);
        exerciceEtat.setExerciceId(exerciceEtat.getExerciceId());
        return exerciceEtatRepository.save(exerciceEtat);
    }
    @MutationMapping
    public ExerciceEtat modifierExerciceEtat(@Argument String idExercice,@Argument boolean isFermer){
        ExerciceEtat exerciceEtat=findByIdExercice(idExercice);
        exerciceEtat.setFermer(isFermer);
        exerciceEtat.setClassement(exerciceEtat.getClassement());
        exerciceEtat.setResultat(exerciceEtat.getResultat());
        exerciceEtat.setExerciceId(exerciceEtat.getExerciceId());
        return exerciceEtatRepository.save(exerciceEtat);
    }
}
