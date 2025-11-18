CREATE TABLE IF NOT EXISTS "user" (
	id					BIGSERIAL PRIMARY KEY NOT NULL,
    kode_opd            VARCHAR(255) REFERENCES opd(kode_opd),
    nip_pegawai         VARCHAR(255) REFERENCES pegawai(nip_pegawai),
    email_user          VARCHAR(255) NOT NULL,
    status_user         VARCHAR(255) NOT NULL,
    level_role_user     VARCHAR(255) NOT NULL,
    nama_role_user      VARCHAR(255) NOT NULL,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now()
);

CREATE INDEX idx_user_kode_opd ON "user"(kode_opd);
CREATE INDEX idx_user_nip_pegawai ON "user"(nip_pegawai);
