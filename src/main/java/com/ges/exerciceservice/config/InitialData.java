package com.ges.exerciceservice.config;

import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.entities.Journale;
import com.ges.exerciceservice.entities.SaisieJournaux;
import com.ges.exerciceservice.enums.State;
import com.ges.exerciceservice.model.Societe;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.repository.JournaleRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import com.ges.exerciceservice.service.SocieteRestClientService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class InitialData {

    private static ExerciceRepository exerciceRepository;

    private static SaisieJournalRepository saisieJournalRepository;
    private  static JournaleRepository journaleRepository;
    private static SocieteRestClientService societeRestClientService;

    public InitialData(ExerciceRepository exerciceRepository, SaisieJournalRepository saisieJournalRepository, JournaleRepository journaleRepository, SocieteRestClientService societeRestClientService) {
        this.exerciceRepository = exerciceRepository;
        this.saisieJournalRepository = saisieJournalRepository;
        this.journaleRepository = journaleRepository;
        this.societeRestClientService = societeRestClientService;
    }

    public  static void creeExercice(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Societe> societes= societeRestClientService.allSocietes().getContent().stream().toList();
        societes.forEach(societe -> {
            for(int i=0;i<5;i++){
                Exercice exercice=Exercice.builder()
                        .id(UUID.randomUUID().toString())
                        .date_debut(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                        .date_fin(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString())
                        .societeId(societe.getId())
                        .build();
                System.out.println("Exercice: "+exerciceRepository.save(exercice).getId());
            }

        });
    }

    public static void creeSaisirJournaux(){
        List<Exercice> exercices=exerciceRepository.findAll();
        exercices.forEach(exercice -> {
            for(int i=0;i<5;i++){
                SaisieJournaux saisieJournaux=SaisieJournaux.builder()
                        .id(UUID.randomUUID().toString())
                        .position(State.VIDE)
                        .periode("janvier."+i)
                        .code("ABC"+i*i)
                        .intitule_journale("XYZ"+Math.random()*i)
                        .exercice(exercice)
                        .build();
                System.out.println("Saisi Journal : "+saisieJournalRepository.save(saisieJournaux).getId());
            }
        });
    }
    public static void creeJounal(){
        Random random=new Random();
        List<SaisieJournaux> saisieJournauxes=saisieJournalRepository.findAll();
        saisieJournauxes.forEach(saisieJournaux -> {
            for(int i=0;i<5;i++){
                Journale journale=Journale.builder()
                        .id(UUID.randomUUID().toString())
                        .jour(i)
                        .numFacture("fact"+i)
                        .ref("HEO"+random.nextInt(200))
                        .numCompte(String.format("%s",random.nextLong(9999)))
                        .numCompteTiere(String.format("%s",random.nextLong(9999)))
                        .libelle("libelle"+i)
                        .credit(1000)
                        .saisieJournaux(saisieJournaux)
                    .build();
                System.out.println("journale: "+journaleRepository.save(journale).getId());
            }
        });
    }
}
