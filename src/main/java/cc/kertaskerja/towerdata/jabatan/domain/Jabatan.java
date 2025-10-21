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

        @Column("kode_pemda")
        String kodePemda,

        @Column("penunjang")
        Boolean penunjang,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Jabatan of (
    		String kodeJabatan,
            String namaJabatan,
            String kodePemda,
            Boolean penunjang
    ) {
        return new Jabatan(
                null,
                kodeJabatan,
                namaJabatan,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
