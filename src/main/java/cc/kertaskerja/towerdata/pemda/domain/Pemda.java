package cc.kertaskerja.towerdata.pemda.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "pemda")
public record Pemda (
        @Id
        Long id,

        @Column("id_pemda")
        String idPemda,

        @Column("kode_pemda")
        String kodePemda,

        @Column("nama_pemda")
        String namaPemda,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
    public static Pemda of (
            String idPemda,
            String kodePemda,
            String namaPemda
    ) {
        return new Pemda(
                null,
                idPemda,
                kodePemda,
                namaPemda,
                null,
                null
        );
    }
}
