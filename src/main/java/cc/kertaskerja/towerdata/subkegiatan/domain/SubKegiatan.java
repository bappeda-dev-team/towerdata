package cc.kertaskerja.towerdata.subkegiatan.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "subkegiatan")
public record SubKegiatan(
    @Id
    Long id,

    @Column("kode_subkegiatan")
    String kodeSubKegiatan,

    @Column("nama_subkegiatan")
    String namaSubKegiatan,

    @Column("status")
    String status,

    @Column("penunjang")
    Boolean penunjang,

    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate
) {
    public static SubKegiatan of (
            String kodeSubKegiatan,
            String namaSubKegiatan,
            String status,
            Boolean penunjang
    ) {
        return new SubKegiatan(
                null,
                kodeSubKegiatan,
                namaSubKegiatan,
                status,
                penunjang,
                null,
                null
        );
    }
}
