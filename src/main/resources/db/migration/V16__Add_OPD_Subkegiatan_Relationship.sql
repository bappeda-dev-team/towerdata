ALTER TABLE subkegiatan
ADD COLUMN kode_opd VARCHAR(255);

ALTER TABLE subkegiatan
ADD CONSTRAINT fk_subkegiatan_kode_opd FOREIGN KEY (kode_opd) REFERENCES opd(kode_opd);

CREATE INDEX idx_subkegiatan_kode_opd ON subkegiatan(kode_opd);

