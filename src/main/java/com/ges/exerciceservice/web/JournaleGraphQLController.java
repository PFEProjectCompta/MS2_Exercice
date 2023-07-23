package com.ges.exerciceservice.web;

import com.ges.exerciceservice.dto.JournalDTO;
import com.ges.exerciceservice.entities.Journale;
import com.ges.exerciceservice.repository.JournaleRepository;
import com.ges.exerciceservice.repository.SaisieJournalRepository;
import com.ges.exerciceservice.service.PlanComptableRestClientService;
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
public class JournaleGraphQLController {
    private JournaleRepository journaleRepository;
    private SaisieJournalRepository saisieJournalRepository;
    private PlanComptableRestClientService planComptableRestClientService;

    public JournaleGraphQLController(JournaleRepository journaleRepository, SaisieJournalRepository saisieJournalRepository, PlanComptableRestClientService planComptableRestClientService) {
        this.journaleRepository = journaleRepository;
        this.saisieJournalRepository = saisieJournalRepository;
        this.planComptableRestClientService = planComptableRestClientService;
    }
    @QueryMapping
    public List<Journale> journaleList(){
        List<Journale> journales=journaleRepository.findAll();
        journales.forEach(journale -> {
            journale.setNumCompte(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteId()));
        });
        journales.forEach(journale -> {
            journale.setNumCompteTiere(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteTiereId()));
        });
        return journales;
    }
    @QueryMapping
    public List<Journale> journaleBySaisieJournal(@Argument String id){
        List<Journale> journales=journaleRepository.findAll();
        List<Journale> journalesBySaisieJournal=new ArrayList<>();
        for(int i=0;i<journales.size();i++){
            if(journales.get(i).getSaisieJournaux().getId().equals(id)){
                journalesBySaisieJournal.add(journales.get(i));
            }
        }
        journalesBySaisieJournal.forEach(journale -> {
            journale.setNumCompte(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteId()));
        });
        journalesBySaisieJournal.forEach(journale -> {
            journale.setNumCompteTiere(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteTiereId()));
        });
        return journalesBySaisieJournal;
    }
    @QueryMapping
    public Journale journaleById(@Argument String id){
        Journale journale=journaleRepository.findById(id).get();
        journale.setNumCompte(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteId()));
        journale.setNumCompteTiere(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journale.getNumCompteTiereId()));
        return journale;
    }
    @MutationMapping
    public Journale ajouterJournale(@Argument JournalDTO journalDTO){
        Journale journale=Journale.builder()
                .id(UUID.randomUUID().toString())
                .numFacture(journalDTO.getNumFacture())
                .ref(journalDTO.getRef())
                .debit(journalDTO.getDebit())
                .credit(journalDTO.getCredit())
                .numCompteTiereId(journalDTO.getNumCompteTiereId())
                .numCompteId( journalDTO.getNumCompteId())
                .numCompteTiere(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"), journalDTO.getNumCompteTiereId()))
                .numCompte(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"), journalDTO.getNumCompteId()))
                .libelle(journalDTO.getLibelle())
                .jour(journalDTO.getJour())
                .saisieJournaux(saisieJournalRepository.findById(journalDTO.getSaisieJournauxId()).get())
                .build();
        journaleRepository.save(journale);
        return journale;
    }
    @MutationMapping
    public Journale updateJournale(@Argument JournalDTO journalDTO,
                                   @Argument String id){
        Journale journale=journaleRepository.findById(id).get();
        journale.setNumFacture(journalDTO.getNumFacture()==null?journale.getNumFacture():journalDTO.getNumFacture());
        journale.setRef(journalDTO.getRef()==null?journale.getRef(): journalDTO.getRef());
        journale.setDebit(journalDTO.getDebit()==0? journale.getDebit():journalDTO.getDebit());
        journale.setCredit(journalDTO.getCredit()==0?journale.getCredit():journalDTO.getCredit());
        journale.setNumCompteTiere(journalDTO.getNumCompteTiereId()==null?journale.getNumCompteTiere():planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journalDTO.getNumCompteTiereId()));
        journale.setNumCompte(journalDTO.getNumCompteId()==null?journale.getNumCompte():planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),journalDTO.getNumCompteId()));
        journale.setLibelle(journalDTO.getLibelle()==null?journale.getLibelle():journalDTO.getLibelle());
        journale.setJour(journalDTO.getJour()==0?journale.getJour():journalDTO.getJour());
        journale.setSaisieJournaux(journalDTO.getSaisieJournauxId()==null?journale.getSaisieJournaux():saisieJournalRepository.findById(journalDTO.getSaisieJournauxId()).get());
        journale.setNumCompteId(journalDTO.getNumCompteId()==null?journale.getNumCompteId():journalDTO.getNumCompteId());
        journale.setNumCompteTiereId(journalDTO.getNumCompteTiereId()==null?journale.getNumCompteTiereId():journalDTO.getNumCompteTiereId());
        journaleRepository.save(journale);
        return journale;
    }
    @MutationMapping
    public Journale deleteJournale(@Argument String id){
        Journale journale=journaleRepository.findById(id).get();
        journaleRepository.delete(journaleRepository.findById(id).get());
        return journale;
    }
}
