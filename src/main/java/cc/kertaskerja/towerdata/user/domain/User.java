package cc.kertaskerja.towerdata.user.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

public record User(
		@Id
        Long id,

        @Column("kode_opd")
        String kodeOpd,

        @Column("nip_pegawai")
        String nipPegawai,

        @Column("email_user")
        String emailUser,

        @Column("status_user")
        String statusUser,

        @Column("level_role_user")
        String levelRoleUser,

        @Column("nama_role_user")
        String namaRoleUser,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static User of (
            String kodeOpd,
            String nipPegawai,
            String emailUser,
            String statusUser,
            String levelRoleUser,
            String namaRoleUser
    ) {
        return new User (
                null,
                kodeOpd,
                nipPegawai,
                emailUser,
                statusUser,
                levelRoleUser,
                namaRoleUser,
                null,
                null
        );
    }
}
