

CREATE TABLE tb_user_admin (
                               id_admin BIGINT NOT NULL PRIMARY KEY,
                               codigo_admin VARCHAR(255) UNIQUE,
                               CONSTRAINT fk_admin_user FOREIGN KEY (id_admin) REFERENCES tb_user (id) ON DELETE CASCADE
);