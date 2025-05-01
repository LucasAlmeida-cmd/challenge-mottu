package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.PatioNotFoundException;
import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.records.PatioRecord;
import com.example.challenge_mottu.records.SecaoResumoRecord;
import com.example.challenge_mottu.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    @Autowired
    PatioRepository repository;

    public Patio adicionar(Patio patio){
        return repository.save(patio);
    }

    public List<PatioRecord> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertToRecord)
                .toList();
    }

    public Patio buscarPorInd(String ident){
        return repository.findByIdentificacao(ident);
    }

    public Patio atualizarPatio(String identificacao, Patio patioObj){
            Patio patio = repository.findByIdentificacao(identificacao);
            if (patio == null)throw new PatioNotFoundException(identificacao);
            patio.setComprimento(patioObj.getComprimento());
            patio.setLargura(patioObj.getLargura());
            patio.setSecoes(patioObj.getSecoes());
            return repository.save(patio);
    }

    public void remover(String ident){
        Patio patio = repository.findByIdentificacao(ident);
        if (patio == null)throw new PatioNotFoundException(ident);
        repository.delete(patio);
    }

    private PatioRecord convertToRecord(Patio patio) {
        return new PatioRecord(
                patio.getIdPatio(),
                patio.getIdentificacao(),
                patio.getLargura(),
                patio.getComprimento(),
                patio.getSecoes().stream()
                        .map(secao -> new SecaoResumoRecord(secao.getIdentificacao()))
                        .toList()
        );
    }
}
