CREATE TABLE IF NOT EXISTS jabatan_pegawai (
	id				BIGSERIAL PRIMARY KEY NOT NULL,
    nip_pegawai         VARCHAR(255) NOT NULL,
    kode_jabatan        VARCHAR(255) NOT NULL,
    kode_pemda          VARCHAR(255) NOT NULL,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now()
);

ALTER TABLE jabatan_pegawai
ADD CONSTRAINT fk_jabatan_pegawai_nip_pegawai FOREIGN KEY (nip_pegawai) REFERENCES pegawai(nip_pegawai);

ALTER TABLE jabatan_pegawai
ADD CONSTRAINT fk_jabatan_pegawai_kode_jabatan FOREIGN KEY (kode_jabatan) REFERENCES jabatan(kode_jabatan);

ALTER TABLE jabatan_pegawai
ADD CONSTRAINT fk_jabatan_pegawai_kode_pemda FOREIGN KEY (kode_pemda) REFERENCES pemda(kode_pemda);

CREATE INDEX idx_jabatan_pegawai_nip_pegawai ON jabatan_pegawai(nip_pegawai);
CREATE INDEX idx_jabatan_pegawai_kode_jabatan ON jabatan_pegawai(kode_jabatan);
CREATE INDEX idx_jabatan_pegawai_kode_pemda ON jabatan_pegawai(kode_pemda);
