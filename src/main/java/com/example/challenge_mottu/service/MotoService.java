package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.MotoNotFoundException;
import com.example.challenge_mottu.exceptions.VagaNotFoundException;
import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.model.Vaga;
import com.example.challenge_mottu.records.MotoRecord;
import com.example.challenge_mottu.repository.MotoRepository;
import com.example.challenge_mottu.repository.MotoqueiroRepository;
import com.example.challenge_mottu.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {


    @Autowired
    MotoRepository repository;

    @Autowired
    MotoqueiroRepository motoqueiroRepository;

    @Autowired
    VagaRepository vagaRepository;

    public Moto cadastrar(MotoRecord motoObj){
        String cpfLimpo = motoObj.motoqueiroCpf().replaceAll("[^0-9]", "");
        Motoqueiro motoqueiro = motoqueiroRepository.findByCpfUser(cpfLimpo);
        Vaga vaga = vagaRepository.findByPatioSecaoENumero(
                        motoObj.patioIdentificacao(),
                        motoObj.secaoIdentificacao(),
                        motoObj.vagaIdentificacao())
                .orElseThrow(() -> new VagaNotFoundException(
                        "Vaga não encontrada com os critérios: " +
                                "Pátio='" + motoObj.patioIdentificacao() + "', " +
                                "Seção='" + motoObj.secaoIdentificacao() + "', " +
                                "Número=" + motoObj.vagaIdentificacao()));
        Moto moto = new Moto();
        moto.setModeloMoto(motoObj.modeloMoto());
        moto.setAnoMoto(motoObj.anoMoto());
        moto.setChassi(motoObj.chassi());
        moto.setStatus(motoObj.status());
        moto.setMotoqueiro(motoqueiro);
        moto.setVaga(vaga);
        vagaRepository.save(vaga);
        return repository.save(moto);
    }

    public List<Moto> listarTodas(){
        return repository.findAll();
    }

    public Moto atualizaPeloChassi(String chassi, Moto moto){
        Moto moto1 = repository.findByChassi(chassi);
        if (moto1 == null)throw new MotoNotFoundException(chassi);
        moto1.setAnoMoto(moto.getAnoMoto());
        moto1.setModeloMoto(moto.getModeloMoto());
        moto1.setMotoqueiro(moto.getMotoqueiro());
        moto1.setStatus(moto.getStatus());
        moto1.setChassi(moto.getChassi());
        return repository.save(moto1);
    }

    public void remover(String chassi){
        Moto moto = repository.findByChassi(chassi);
        if (moto == null) throw new MotoNotFoundException(chassi);
        if (moto.getMotoqueiro() != null) {
            Motoqueiro motoqueiro = moto.getMotoqueiro();
            motoqueiro.setMoto(null);
            motoqueiroRepository.save(motoqueiro);
        }
        repository.delete(moto);
    }
}
