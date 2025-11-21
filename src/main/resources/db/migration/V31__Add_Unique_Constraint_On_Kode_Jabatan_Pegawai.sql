ALTER TABLE pegawai
ADD CONSTRAINT uk_pegawai_kode_jabatan UNIQUE (kode_jabatan);
