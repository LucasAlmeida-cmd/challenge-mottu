package com.example.challenge_mottu.service;

import com.example.challenge_mottu.dto.EnderecoDTO;
import com.example.challenge_mottu.model.Endereco;
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

       EnderecoDTO response = restTemplate.getForObject(urlVia, EnderecoDTO.class);

       if (response == null) {
           throw new RuntimeException("CEP não encontrado");
       }

       return new Endereco(
               response.getCep(),
               response.getLogradouro(),
               response.getComplemento(),
               response.getBairro(),
               response.getLocalidade(),
               response.getUf()
       );
   }


}
