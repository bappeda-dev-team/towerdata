ALTER TABLE pegawai
ADD COLUMN opd_id BIGINT REFERENCES opd(id);

CREATE INDEX idx_pegawai_opd_id ON pegawai(opd_id);