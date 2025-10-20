package cc.kertaskerja.towerdata.program.domain;

import cc.kertaskerja.towerdata.kegiatan.domain.Kegiatan;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "program")
public record Program(
        @Id
        Long id,

        @Column("kode_program")
        String kodeProgram,

        @Column("nama_program")
        String namaProgram,

        @Column("kode_pemda")
        String kodePemda,

        @Column("penunjang")
        Boolean penunjang,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Program of (
            String kodeProgram,
            String namaProgram,
            String kodePemda,
            Boolean penunjang
    ) {
        return new Program(
                null,
                kodeProgram,
                namaProgram,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
