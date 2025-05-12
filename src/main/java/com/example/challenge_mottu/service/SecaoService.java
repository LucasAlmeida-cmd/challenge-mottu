package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.SecaoNotFoundException;
import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.model.Secao;
import com.example.challenge_mottu.records_DTOs.SecaoRecord;
import com.example.challenge_mottu.repository.PatioRepository;
import com.example.challenge_mottu.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecaoService {


    @Autowired
    SecaoRepository secaoRepository;
    @Autowired
    PatioRepository patioRepository;

    public Secao adicionar(SecaoRecord secaoObj){
        Patio patio = patioRepository.findByIdentificacao(secaoObj.identificacaoPatio());
        Secao secao = new Secao();
        secao.setIdentificacao(secaoObj.identificacao());
        secao.setPatio(patio);
        Secao savedSecao = secaoRepository.save(secao);
        return secaoRepository.save(savedSecao);
    }

    public List<Secao>listarTodos(){
        return secaoRepository.findAll();
    }

    public Secao atualizar(String ind, Secao secaoObj){
        Secao secao = secaoRepository.findByIdentificacao(ind);
        if (secao == null) throw new SecaoNotFoundException(ind);
        secao.setVagas(secaoObj.getVagas());
        return secaoRepository.save(secao);
    }

    public void remover(String ind){
        Secao secao = secaoRepository.findByIdentificacao(ind);
        if (secao == null) throw new SecaoNotFoundException(ind);
        secaoRepository.delete(secao);
    }

    public void deletarSecaoPorNomeEPatio(String nomeSecao, String nomePatio) {
        Secao secao = secaoRepository.findByNomeAndNomePatio(nomeSecao, nomePatio)
                .orElseThrow(() -> new SecaoNotFoundException("Seção não encontrada"));

        secaoRepository.delete(secao);
    }



}

