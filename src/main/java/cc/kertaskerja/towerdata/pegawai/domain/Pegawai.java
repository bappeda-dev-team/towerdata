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

        @Column("kode_jabatan")
        String kodeJabatan,

        @Column("nama_jabatan")
        String namaJabatan,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Pegawai of (
            String nipPegawai,
            String namaPegawai,
            String kodeOpd,
            String kodeJabatan,
            String namaJabatan
    ) {
        return new Pegawai (
                null,
                nipPegawai,
                namaPegawai,
                kodeOpd,
                kodeJabatan,
                namaJabatan,
                null,
                null
        );
    }
}
