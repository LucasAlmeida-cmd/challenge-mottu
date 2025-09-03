package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.UsuarioNotFoundException;
import com.example.challenge_mottu.model.Administrador;
import com.example.challenge_mottu.model.Role;
import com.example.challenge_mottu.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador adicionar(Administrador administrador){
        administrador.setRole(Role.ADMIN);
        administrador.setPassword(passwordEncoder.encode(administrador.getPassword()));
        return repository.save(administrador);
    }

    public List<Administrador> listarTodos(){
        return repository.findAll();
    }

    public void atualizarAdminPorCpf(String codigo, Administrador administradorAtualizado) {
        Administrador administrador = repository.findByCodigo(codigo);
        if (administrador == null) {
            throw new UsuarioNotFoundException(codigo);
        }
        administrador.setNomeUser(administradorAtualizado.getNomeUser());
        administrador.setDataAniversario(administradorAtualizado.getDataAniversario());
        administrador.setEmail(administradorAtualizado.getEmail());
        administrador.setPassword(passwordEncoder.encode(administradorAtualizado.getPassword()));
        repository.save(administrador);
    }

    public void removerAdmin(Long id) {
        Optional<Administrador> administrador = repository.findById(id);
        repository.deleteById(id);
    }

    public Administrador buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

}
