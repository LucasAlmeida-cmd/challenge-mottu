package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.UsuarioNotFoundException;
import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public Administrador adicionar(Administrador administrador){
        return repository.save(administrador);
    }

    public List<Administrador> listarTodos(){
        return repository.findAll();
    }

    public Administrador atualizarAdminPorCpf(String cpf, Administrador administradorAtualizado) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        Administrador administrador = repository.findByCpfUser(cpfLimpo);
        if (administrador == null) {
            throw new UsuarioNotFoundException(cpf);
        }
        administrador.setNomeUser(administradorAtualizado.getNomeUser());
        administrador.setDataAniversario(administradorAtualizado.getDataAniversario());
        return repository.save(administrador);
    }

    public void removerAdmin(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        Administrador administrador = repository.findByCpfUser(cpfLimpo);

        repository.deleteById(administrador.getId());
    }

}
