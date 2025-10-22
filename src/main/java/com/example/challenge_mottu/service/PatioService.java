package com.example.challenge_mottu.service;

import com.example.challenge_mottu.exceptions.PatioNotFoundException;
import com.example.challenge_mottu.model.Patio;
import com.example.challenge_mottu.records_DTOs.PatioRecord;
import com.example.challenge_mottu.records_DTOs.SecaoResumoRecord;
import com.example.challenge_mottu.repository.PatioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import org.hibernate.Session;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import java.util.List;

@Service
public class PatioService {

    @Autowired
    PatioRepository repository;
    @Autowired
    private EntityManager em;

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

    //chamada procedure

    @Transactional
    public String chamarProcedureDeVagas(Long patioId) {
        Session session = em.unwrap(Session.class);
        String jsonResultado = session.doReturningWork(new org.hibernate.jdbc.ReturningWork<String>() {
            @Override
            public String execute(Connection connection) {
                try {
                    String plsql = "{call pr_patio_secoes_vagas(?, ?)}";

                    try (CallableStatement cs = connection.prepareCall(plsql)) {
                        cs.setBigDecimal(1, new BigDecimal(patioId));
                        cs.registerOutParameter(2, Types.VARCHAR);
                        cs.execute();
                        return cs.getString(2);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao chamar a procedure via JDBC: " + e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return jsonResultado;
    }

    @Transactional
    public String chamarFnValidaIdade(Long userId) {
        Session session = em.unwrap(Session.class);

        String resultado = session.doReturningWork(connection -> {
            try {
                // Sintaxe JDBC para chamar uma FUNÇÃO: {? = call nome_funcao(?)}
                String sql = "{? = call fn_valida_idade_user(?)}";

                try (CallableStatement cs = connection.prepareCall(sql)) {

                    // 1. Registrar o parâmetro de RETORNO (OUT, Posição 1)
                    cs.registerOutParameter(1, Types.VARCHAR);

                    // 2. Setar o parâmetro de ENTRADA (IN, Posição 2)
                    cs.setBigDecimal(2, new BigDecimal(userId));

                    cs.execute();

                    // 3. Pegar o resultado do RETORNO (Posição 1)
                    return cs.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao chamar função: " + e.getMessage();
            }
        });
        return resultado;
    }

    @Transactional
    public String chamarPrGerarRelatorioVagas() {
        Session session = em.unwrap(Session.class);

        String relatorio = session.doReturningWork(connection -> {
            try {
                // Sintaxe JDBC para procedure com 1 parâmetro OUT
                String sql = "{call pr_gerar_relatorio_vagas(?)}";

                try (CallableStatement cs = connection.prepareCall(sql)) {

                    // 1. Registrar o parâmetro OUT (Posição 1)
                    // Usamos CLOB pois o relatório pode ser grande
                    cs.registerOutParameter(1, Types.CLOB);

                    cs.execute();

                    // 2. Pegar o resultado do parâmetro OUT (Posição 1)
                    return cs.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao chamar procedure: " + e.getMessage();
            }
        });
        return relatorio;
    }

}
