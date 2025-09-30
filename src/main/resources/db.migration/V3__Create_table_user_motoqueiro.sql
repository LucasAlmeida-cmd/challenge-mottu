
CREATE TABLE tb_user_motoqueiro (
                                    id_motoqueiro BIGINT NOT NULL PRIMARY KEY,
                                    cnh_motoqueiro VARCHAR(9) NOT NULL,
                                    cpf_usuario VARCHAR(14) NOT NULL UNIQUE,
                                    cep VARCHAR(255),
                                    logradouro VARCHAR(255),
                                    complemento VARCHAR(255),
                                    bairro VARCHAR(255),
                                    localidade VARCHAR(255),
                                    uf VARCHAR(255),
                                    CONSTRAINT fk_motoqueiro_user FOREIGN KEY (id_motoqueiro) REFERENCES tb_user (id) ON DELETE CASCADE
);