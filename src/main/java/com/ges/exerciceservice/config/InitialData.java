package com.ges.exerciceservice.config;

import com.ges.exerciceservice.entities.Exercice;
import com.ges.exerciceservice.entities.Journale;
import com.ges.exerciceservice.entities.SaisieJournaux;
import com.ges.exerciceservice.enums.State;
import com.ges.exerciceservice.model.PlanComptableElement;
import com.ges.exerciceservice.model.Societe;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.repository.JournaleRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import com.ges.exerciceservice.service.PlanComptableRestClientService;
import com.ges.exerciceservice.service.SocieteRestClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class InitialData {

    private static ExerciceRepository exerciceRepository;

    private static SaisieJournalRepository saisieJournalRepository;
    private  static JournaleRepository journaleRepository;
    private static SocieteRestClientService societeRestClientService;
    private static PlanComptableRestClientService planComptableRestClientService;

    public InitialData(ExerciceRepository exerciceRepository, SaisieJournalRepository saisieJournalRepository, JournaleRepository journaleRepository, SocieteRestClientService societeRestClientService, PlanComptableRestClientService planComptableRestClientService) {
        this.exerciceRepository = exerciceRepository;
        this.saisieJournalRepository = saisieJournalRepository;
        this.journaleRepository = journaleRepository;
        this.societeRestClientService = societeRestClientService;
        this.planComptableRestClientService = planComptableRestClientService;
    }

    public  static void creeExercice(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Societe> societes= societeRestClientService.allSocietes(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization")).getContent().stream().toList();
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
                        .build();
                System.out.println("Saisi Journal : "+saisieJournalRepository.save(saisieJournaux).getId());
            }
        });
    }
    public static void creeJounal(){
        Random random=new Random();
        List<PlanComptableElement> planComptableElements=planComptableRestClientService.allplanComptableElements(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization")).getContent().stream().toList();
        List<SaisieJournaux> saisieJournauxes=saisieJournalRepository.findAll();
        saisieJournauxes.forEach(saisieJournaux -> {
            for(int i=0;i<5;i++){
                Journale journale=Journale.builder()
                        .id(UUID.randomUUID().toString())
                        .jour(i)
                        .numFacture("fact"+i)
                        .ref("HEO"+random.nextInt(200))
                        .numCompteId(planComptableElements.get(random.nextInt(19)).getId())
                        .numCompteTiereId(planComptableElements.get(random.nextInt(19)).getId())
                        .libelle("libelle"+i)
                        .credit(1000)
                        .saisieJournaux(saisieJournaux)
                    .build();
                System.out.println("journale: "+journaleRepository.save(journale).getId());
            }
        });
    }
}
