CREATE TABLE IF NOT EXISTS pemda (
	id					BIGSERIAL PRIMARY KEY NOT NULL,
	id_pemda            VARCHAR(255) NOT NULL,
	kode_pemda          VARCHAR(255) NOT NULL UNIQUE,
    nama_pemda          VARCHAR(255) NOT NULL,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now()
);
