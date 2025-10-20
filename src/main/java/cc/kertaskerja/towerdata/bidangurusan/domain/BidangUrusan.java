package cc.kertaskerja.towerdata.bidangurusan.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "bidang_urusan")
public record BidangUrusan(
		@Id
        Long id,

        @Column("kode_bidang_urusan")
        String kodeBidangUrusan,

        @Column("nama_bidang_urusan")
        String namaBidangUrusan,

        @Column("kode_pemda")
        String kodePemda,

        @Column("penunjang")
        Boolean penunjang,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
	public static BidangUrusan of (
    		String kodeBidangUrusan,
            String namaBidangUrusan,
            String kodePemda,
            Boolean penunjang
    ) {
        return new BidangUrusan(
                null,
                kodeBidangUrusan,
                namaBidangUrusan,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
