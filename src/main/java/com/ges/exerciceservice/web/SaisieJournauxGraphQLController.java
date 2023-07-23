package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.SaisieJournalDTO;
import com.ges.exerciceservice.entities.CodeJournaux;
import com.ges.exerciceservice.entities.SaisieJournaux;
import com.ges.exerciceservice.enums.State;
import com.ges.exerciceservice.repository.CodeJournauxRepository;
import com.ges.exerciceservice.repository.ExerciceRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;

@Controller
public class SaisieJournauxGraphQLController {

    @Autowired
    private SaisieJournalRepository saisieJournalRepository;
    @Autowired
    private CodeJournauxRepository codeJournauxRepository;

    @QueryMapping
    public List<SaisieJournaux> saisiJournauxList(){
        return saisieJournalRepository.findAll();
    }
    @QueryMapping
    public SaisieJournaux saisieJournalById(@Argument String id){
        return saisieJournalRepository.findById(id).get();
    }
    @QueryMapping
    public List<SaisieJournaux> saisieJournauxByExercice(@Argument String idExercice){
        List<SaisieJournaux> saisieJournauxes=saisieJournalRepository.findAll();
        List<SaisieJournaux> saisieJournauxesByCodeJournal=new ArrayList<>();
        for(int i=0;i<saisieJournauxes.size();i++){
            if(saisieJournauxes.get(i).getCode_Journal().getExercice().getId().equals(idExercice)){
                saisieJournauxesByCodeJournal.add(saisieJournauxes.get(i));
            }
        }
        Collections.sort(saisieJournauxesByCodeJournal, Comparator.comparingInt(SaisieJournaux::getClassement));
        return saisieJournauxesByCodeJournal;
    }
    @QueryMapping
    public List<SaisieJournaux> saisieJournauxByCodeJournal(@Argument String id){
        List<SaisieJournaux> saisieJournauxes=saisieJournalRepository.findAll();
        List<SaisieJournaux> saisieJournauxesByCodeJournal=new ArrayList<>();
        for(int i=0;i<saisieJournauxes.size();i++){
            if(saisieJournauxes.get(i).getCode_Journal().getId().equals(id)){
                saisieJournauxesByCodeJournal.add(saisieJournauxes.get(i));
            }
        }
        Collections.sort(saisieJournauxesByCodeJournal, Comparator.comparingInt(SaisieJournaux::getClassement));
        return saisieJournauxesByCodeJournal;
    }

    @MutationMapping
    public SaisieJournaux ajouterSaisieJournaux(@Argument SaisieJournalDTO saisiJournal){
        SaisieJournaux saisieJournaux=SaisieJournaux.builder()
                .id(UUID.randomUUID().toString())
                .position(saisiJournal.getPosition())
                .code(saisiJournal.getCode())
                .intitule_journale(saisiJournal.getIntitule_journale())
                .periode(saisiJournal.getPeriode())
                .code_Journal(codeJournauxRepository.findById(saisiJournal.getCode_Journal_id()).get())
                .build();
        saisieJournalRepository.save(saisieJournaux);
        return saisieJournaux;
    }
    @MutationMapping
    public String ajouterAllSaisieJournaux(@Argument String idCodeJournal) throws ParseException {
        CodeJournaux codeJournaux=codeJournauxRepository.findById(idCodeJournal).get();

        String dateStringDebut =codeJournaux.getExercice().getDate_debut();
        String dateStringFin =codeJournaux.getExercice().getDate_fin();
        String dateFormatPattern="dd-MM-yyyy";
        LocalDate dateDebut = LocalDate.parse(dateStringDebut, DateTimeFormatter.ofPattern(dateFormatPattern));
        LocalDate dateFin = LocalDate.parse(dateStringFin, DateTimeFormatter.ofPattern(dateFormatPattern));
        Period period = Period.between(dateDebut, dateFin);
        int monthsBetweenDates = period.getYears() * 12 + period.getMonths(); //plus 1
        System.out.println("Date debut : " + dateDebut + " / Date fin: "+dateFin +" = "+monthsBetweenDates);
        for (int i = 0; i <= monthsBetweenDates; i++) {
            LocalDate currentDate = dateDebut.plusMonths(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM.yy");
            String formattedDate = currentDate.format(formatter);

            SaisieJournaux saisieJournaux=SaisieJournaux.builder()
                .id(UUID.randomUUID().toString())
                .position(State.VIDE)
                .code(codeJournaux.getCode())
                .intitule_journale(codeJournaux.getIntitule_journale())
                .periode(formattedDate)
                .code_Journal(codeJournaux)
                .classement(i)
                .build();
            saisieJournalRepository.save(saisieJournaux);
        }




        return "saisieJournaux";
    }
    @MutationMapping
    public SaisieJournaux updateSaisieJournaux(@Argument SaisieJournalDTO saisiJournal,
                                               @Argument String id){
        SaisieJournaux saisieJournaux=saisieJournalRepository.findById(id).get();
        saisieJournaux.setCode(saisiJournal.getCode()==null? saisieJournaux.getCode() : saisiJournal.getCode());
        saisieJournaux.setPeriode(saisiJournal.getPeriode()==null? saisieJournaux.getPeriode():saisiJournal.getPeriode());
        saisieJournaux.setPosition(saisiJournal.getPosition()==null? saisieJournaux.getPosition():saisiJournal.getPosition());
        saisieJournaux.setIntitule_journale(saisiJournal.getIntitule_journale()==null? saisieJournaux.getIntitule_journale():saisiJournal.getIntitule_journale());
        saisieJournaux.setClassement(saisieJournaux.getClassement());
        saisieJournaux.setCode_Journal(saisiJournal.getCode_Journal_id()==null?saisieJournaux.getCode_Journal() :codeJournauxRepository.findById(saisiJournal.getCode_Journal_id()).get());
        saisieJournalRepository.save(saisieJournaux);
        return saisieJournaux;
    }
    @MutationMapping
    public SaisieJournaux deleteSaisieJournaux(@Argument String id){
        SaisieJournaux saisieJournaux=saisieJournalRepository.findById(id).get();
        saisieJournalRepository.delete(saisieJournalRepository.findById(id).get());
        return saisieJournaux ;
    }
}
