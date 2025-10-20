CREATE TABLE IF NOT EXISTS urusan (
	id                  BIGSERIAL PRIMARY KEY NOT NULL,
    kode_urusan         VARCHAR(255) NOT NULL,
    nama_urusan         VARCHAR(255) NOT NULL,
    kode_pemda          VARCHAR(255) NOT NULL,
    penunjang           BOOLEAN,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now()
);