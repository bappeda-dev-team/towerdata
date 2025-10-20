CREATE TABLE IF NOT EXISTS kegiatan (
	id                  BIGSERIAL PRIMARY KEY NOT NULL,
    kode_kegiatan       VARCHAR(255) NOT NULL,
    nama_kegiatan       VARCHAR(255) NOT NULL,
    kode_pemda          VARCHAR(255) NOT NULL,
    penunjang           BOOLEAN,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now()
);