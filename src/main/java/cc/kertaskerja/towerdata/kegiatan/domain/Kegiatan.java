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

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Kegiatan of (
    		String kodeKegiatan,
            String namaKegiatan
    ) {
        return new Kegiatan(
                null,
                kodeKegiatan,
                namaKegiatan,
                null,
                null
        );
    }
}
