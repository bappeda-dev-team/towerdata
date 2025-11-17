ALTER TABLE opd
ADD CONSTRAINT fk_opd_kode_pemda
FOREIGN KEY (kode_pemda) REFERENCES pemda(kode_pemda);

CREATE INDEX idx_opd_kode_pemda ON opd(kode_pemda);