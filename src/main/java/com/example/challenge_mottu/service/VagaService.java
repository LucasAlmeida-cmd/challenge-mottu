package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.VagaException;
import com.example.challenge_mottu.exceptions.VagaNotFoundException;
import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.model.Secao;
import com.example.challenge_mottu.model.Vaga;
import com.example.challenge_mottu.records_DTOs.VagaRecord;
import com.example.challenge_mottu.repository.PatioRepository;
import com.example.challenge_mottu.repository.SecaoRepository;
import com.example.challenge_mottu.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    VagaRepository vagaRepository;
    @Autowired
    SecaoRepository secaoRepository;
    @Autowired
    PatioRepository patioRepository;

public Vaga adicionar(VagaRecord vagaObj) {
    if (vagaObj == null || vagaObj.secaoIdentificacao() == null || vagaObj.padioIdentificacao() == null) {
        throw new IllegalArgumentException("Dados da vaga inválidos");
    }
    Patio patio = patioRepository.findByIdentificacao(vagaObj.padioIdentificacao());
    Secao secao = secaoRepository.findByIdentificacaoAndPatio(vagaObj.secaoIdentificacao(), patio);
    if (vagaRepository.existsBySecaoAndNumeroVaga(secao, vagaObj.numeroVaga())) {
        throw new VagaException("Já existe uma vaga com número " +
                vagaObj.numeroVaga() + " na seção '" + vagaObj.secaoIdentificacao() + "'");
    }
    Vaga vaga = new Vaga();
    vaga.setNumeroVaga(vagaObj.numeroVaga());
    vaga.setDisponivel(vagaObj.disponivel());
    vaga.setSecao(secao);

    return vagaRepository.save(vaga);
}

    public List<Vaga> listarTodos(){
        return vagaRepository.findAll();
    }

    public Vaga atualizar(Integer num, Vaga vagaObj){
        Vaga vaga = vagaRepository.findByNumeroVaga(num);
        if (vaga == null)throw new VagaNotFoundException("a");
        vaga.setNumeroVaga(vagaObj.getNumeroVaga());
        vaga.setSecao(vagaObj.getSecao());
        return vagaRepository.save(vaga);
    }

    public void deletar(Integer num){
        Vaga vaga = vagaRepository.findByNumeroVaga(num);
        if (vaga == null)throw new VagaNotFoundException("a");
        vagaRepository.delete(vaga);
    }


    public Vaga atualizarVaga(String identVaga, String identSecao, String identPatio, Vaga novaVaga) {
        Vaga vaga = vagaRepository.findByIdentificadores(identVaga, identSecao, identPatio)
                .orElseThrow(() -> new VagaNotFoundException("Vaga não encontrada."));
        vaga.setDisponivel(novaVaga.isDisponivel());
        return vagaRepository.save(vaga);
    }
}
