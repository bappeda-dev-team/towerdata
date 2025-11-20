ALTER TABLE pegawai
ADD COLUMN kode_jabatan VARCHAR(255);

ALTER TABLE pegawai
ADD CONSTRAINT fk_pegawai_kode_jabatan FOREIGN KEY (kode_jabatan) REFERENCES jabatan(kode_jabatan);

