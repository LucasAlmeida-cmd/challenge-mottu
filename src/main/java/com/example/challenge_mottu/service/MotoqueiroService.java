package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.UsuarioNotFoundException;
import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.model.Role;
import com.example.challenge_mottu.repository.MotoRepository;
import com.example.challenge_mottu.repository.MotoqueiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoqueiroService {


    @Autowired
    MotoqueiroRepository repository;
    @Autowired
    ViaCepService viaCepService;
    @Autowired
    MotoRepository motoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public void cadastrar(Motoqueiro motoqueiro){
        motoqueiro.setEndereco(viaCepService.buscarEnderecoPorCEP(motoqueiro.getEndereco().getCep()));
        motoqueiro.setPassword(passwordEncoder.encode(motoqueiro.getPassword()));
        motoqueiro.setRole(Role.MOTOQUEIRO);
        repository.save(motoqueiro);
    }

    public List<Motoqueiro> listarTodos(){
        return repository.findAll();
    }

    public void atualiza (String cpf, Motoqueiro motoqueiro){
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        Motoqueiro motoqueiro1 = repository.findByCpfUser(cpfLimpo);
        if (motoqueiro1 == null){
            throw new UsuarioNotFoundException(cpf);
        }
        motoqueiro1.setDataAniversario(motoqueiro.getDataAniversario());
        motoqueiro1.setNomeUser(motoqueiro.getNomeUser());
        motoqueiro1.setEndereco(viaCepService.buscarEnderecoPorCEP(motoqueiro.getEndereco().getCep()));
        motoqueiro1.setPassword(passwordEncoder.encode(motoqueiro.getPassword()));
        motoqueiro1.setEmail(motoqueiro.getEmail());
        repository.save(motoqueiro1);
    }

    @Transactional
    public void remover(String cpf){
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        Motoqueiro motoqueiro = repository.findByCpfUser(cpfLimpo);
        List<Moto> motos = motoRepository.findByMotoqueiro(motoqueiro);
        for (Moto moto : motos) {
            moto.setMotoqueiro(null);
            motoRepository.save(moto);
        }
        if (motoqueiro == null){
            throw new UsuarioNotFoundException(cpf);
        }
        repository.deleteByCpfUser(motoqueiro.getCpfUser());
    }
    public Motoqueiro buscarPorCpf(String cpf) {
        return repository.findByCpfUser(cpf);
    }
    public Motoqueiro buscarPorEmail(String email){
        return repository.findByEmail(email);
    }
}
