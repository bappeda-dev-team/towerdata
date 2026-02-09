ALTER TABLE opd
    DROP CONSTRAINT IF EXISTS fk_opd_kode_pemda;

DROP INDEX IF EXISTS idx_opd_kode_pemda;

ALTER TABLE opd
    DROP COLUMN IF EXISTS kode_pemda,
    DROP COLUMN IF EXISTS sub_opd;
