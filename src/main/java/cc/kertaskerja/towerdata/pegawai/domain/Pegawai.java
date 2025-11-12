package cc.kertaskerja.towerdata.pegawai.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "pegawai")
public record Pegawai(
        @Id
        Long id,

        @Column("kode_pegawai")
        String kodePegawai,

        @Column("nama_pegawai")
        String namaPegawai,

        @Column("kode_pemda")
        String kodePemda,

        @Column("opd_id")
        Long opdId,

        @Column("penunjang")
        Boolean penunjang,

        @Column("nama_role_pegawai")
        String namaRolePegawai,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Pegawai of (
            String kodePegawai,
            String namaPegawai,
            String kodePemda,
            Long opdId,
            Boolean penunjang,
            String namaRolePegawai
    ) {
        return new Pegawai (
                null,
                kodePegawai,
                namaPegawai,
                kodePemda,
                opdId,
                penunjang,
                namaRolePegawai,
                null,
                null
        );
    }
}
