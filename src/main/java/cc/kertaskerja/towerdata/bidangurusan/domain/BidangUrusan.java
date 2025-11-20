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

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
	public static BidangUrusan of (
    		String kodeBidangUrusan,
            String namaBidangUrusan
    ) {
        return new BidangUrusan(
                null,
                kodeBidangUrusan,
                namaBidangUrusan,
                null,
                null
        );
    }
}
