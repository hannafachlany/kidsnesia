

CREATE TABLE pelanggan (
    id_pelanggan SERIAL,
    nama_pelanggan VARCHAR(225) NOT NULL,
    password VARCHAR(225) NOT NULL,
    email VARCHAR(225) NOT NULL,
    no_hp_pelanggan VARCHAR(225) NOT NULL,
    token VARCHAR(225),
    token_expired_at BIGINT,
    PRIMARY KEY (id_pelanggan),
    UNIQUE (email),
    UNIQUE (token)
);

select * from pelanggan;
desc pelanggan;