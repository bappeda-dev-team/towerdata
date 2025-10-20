package cc.kertaskerja.towerdata.urusan.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "urusan")
public record Urusan(
		@Id
	    Long id,
	    
	    @Column("kode_urusan")
	    String kodeUrusan,

	    @Column("nama_urusan")
	    String namaUrusan,

	    @Column("kode_pemda")
	    String kodePemda,

	    @Column("penunjang")
	    Boolean penunjang,

	    @CreatedDate
	    Instant createdDate,

	    @LastModifiedDate
	    Instant lastModifiedDate
) {
	public static Urusan of (
            String 	kodeUrusan,
            String 	namaUrusan,
            String 	kodePemda,
            Boolean penunjang
    ) {
        return new Urusan(
                null,
                kodeUrusan,
                namaUrusan,
                kodePemda,
                penunjang,
                null,
                null
        );
    }
}
