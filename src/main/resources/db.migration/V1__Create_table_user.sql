

CREATE TABLE tb_user (
                         id BIGSERIAL PRIMARY KEY,
                         user_type VARCHAR(31) NOT NULL,
                         nome_usuario VARCHAR(80) NOT NULL,
                         data_aniversario_usuario DATE NOT NULL,
                         senha_usuario VARCHAR(100) NOT NULL,
                         email_usuario VARCHAR(50) NOT NULL,
                         role VARCHAR(255)
);