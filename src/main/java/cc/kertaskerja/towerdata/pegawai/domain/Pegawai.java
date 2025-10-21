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

        @Column("penunjang")
        Boolean penunjang,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Pegawai of (
            String kodePegawai,
            String namaPegawai,
            String kodePemda,
            Boolean penunjang
    ) {
        return new Pegawai (
                null,
                kodePegawai,
                namaPegawai,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
