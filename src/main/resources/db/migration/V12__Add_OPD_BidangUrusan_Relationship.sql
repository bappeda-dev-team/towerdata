ALTER TABLE bidang_urusan
ADD COLUMN opd_id BIGINT REFERENCES opd(id);

CREATE INDEX idx_bidang_urusan_opd_id ON bidang_urusan(opd_id);
