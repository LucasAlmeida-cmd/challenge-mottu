

-- Inserindo um Administrador
INSERT INTO tb_user (id, user_type, nome_usuario, data_aniversario_usuario, senha_usuario, email_usuario, role)
VALUES (1, 'ADMIN', 'Admin Master', '1990-01-15', 'admin', 'admin', 'ADMIN');
INSERT INTO tb_user_admin (id_admin, codigo_admin)
VALUES (1, 'ADM-001');

-- Inserindo um Motoqueiro
INSERT INTO tb_user (id, user_type, nome_usuario, data_aniversario_usuario, senha_usuario, email_usuario, role)
VALUES (2, 'MOTOQUEIRO', 'Carlos Pereira', '1995-08-20', 'motoqueiro456', 'carlos.p@email.com', 'USER');
INSERT INTO tb_user_motoqueiro (id_motoqueiro, cnh_motoqueiro, cpf_usuario, cep, logradouro, complemento, bairro, localidade, uf)
VALUES (2, '123456789', '11122233344', '01001-000', 'Praça da Sé', 'lado ímpar', 'Sé', 'São Paulo', 'SP');