package com.example.challenge_mottu.service;

import com.example.challenge_mottu.model.Endereco;
import com.example.challenge_mottu.records_DTOs.EnderecoRecords;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;

    public ViaCepService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

   public Endereco buscarEnderecoPorCEP(String cep){
       String urlVia = "https://viacep.com.br/ws/"+ cep +"/json/";

       EnderecoRecords response = restTemplate.getForObject(urlVia, EnderecoRecords.class);

       if (response == null) {
           throw new RuntimeException("CEP não encontrado");
       }

       return new Endereco(
               response.cep(),
               response.logradouro(),
               response.complemento(),
               response.bairro(),
               response.localidade(),
               response.uf()
       );
   }


}
