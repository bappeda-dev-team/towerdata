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

        @Column("nip_pegawai")
        String nipPegawai,

        @Column("nama_pegawai")
        String namaPegawai,

        @Column("kode_opd")
        String kodeOpd,

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
            String nipPegawai,
            String namaPegawai,
            String kodeOpd,
            Boolean penunjang,
            String namaRolePegawai
    ) {
        return new Pegawai (
                null,
                nipPegawai,
                namaPegawai,
                kodeOpd,
                penunjang,
                namaRolePegawai,
                null,
                null
        );
    }
}
