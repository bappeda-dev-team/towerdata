package cc.kertaskerja.towerdata.kegiatan.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "kegiatan")
public record Kegiatan(
        @Id
        Long id,

        @Column("kode_kegiatan")
        String kodeKegiatan,

        @Column("nama_kegiatan")
        String namaKegiatan,

        @Column("kode_pemda")
        String kodePemda,

        @Column("penunjang")
        Boolean penunjang,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Kegiatan of (
    		String kodeKegiatan,
            String namaKegiatan,
            String kodePemda,
            Boolean penunjang
    ) {
        return new Kegiatan(
                null,
                kodeKegiatan,
                namaKegiatan,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
