package cc.kertaskerja.towerdata.jabatan.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "jabatan")
public record Jabatan(
        @Id
        Long id,

        @Column("kode_jabatan")
        String kodeJabatan,

        @Column("nama_jabatan")
        String namaJabatan,

        @Column("struktural")
        Boolean struktural,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Jabatan of (
            String kodeJabatan,
            String namaJabatan,
            Boolean struktural
    ) {
        return new Jabatan(
                null,
                kodeJabatan,
                namaJabatan,
                struktural,
                null,
                null
        );
    }
}
