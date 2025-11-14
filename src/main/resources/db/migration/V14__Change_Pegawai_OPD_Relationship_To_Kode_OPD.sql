ALTER TABLE pegawai
ADD COLUMN kode_opd VARCHAR(255);

UPDATE pegawai p
SET kode_opd = o.kode_opd
FROM opd o
WHERE p.opd_id = o.id;

ALTER TABLE pegawai
ALTER COLUMN kode_opd SET NOT NULL;

ALTER TABLE pegawai
ADD CONSTRAINT fk_pegawai_kode_opd FOREIGN KEY (kode_opd) REFERENCES opd(kode_opd);

ALTER TABLE pegawai
DROP COLUMN opd_id;

