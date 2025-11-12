-- Add foreign key relationship between pegawai and opd tables
-- One OPD can have many pegawai (employees)

ALTER TABLE pegawai
ADD COLUMN opd_id BIGINT REFERENCES opd(id);

-- Create index on foreign key for better query performance
CREATE INDEX idx_pegawai_opd_id ON pegawai(opd_id);