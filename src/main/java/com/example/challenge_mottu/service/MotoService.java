package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.MotoNotFoundException;
import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.repository.MotoRepository;
import com.example.challenge_mottu.repository.MotoqueiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {


    @Autowired
    MotoRepository repository;

    @Autowired
    MotoqueiroRepository motoqueiroRepository;

    public Moto cadastrar(Moto moto){
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
