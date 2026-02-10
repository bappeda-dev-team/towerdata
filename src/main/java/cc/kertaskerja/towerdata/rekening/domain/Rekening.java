package cc.kertaskerja.towerdata.rekening.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name="rekening")
public record Rekening(
		@Id
		Long id,

		@Column("kode_rekening")
		String kodeRekening,

		@Column("nama_rekening")
		String namaRekening,

		@CreatedDate
		Instant createdDate,

		@LastModifiedDate
		Instant lastModifiedDate
) {
	public static Rekening of (
			String kodeRekening,
			String namaRekening
	) {
		return new Rekening(
				null,
				kodeRekening,
				namaRekening,
				null,
				null
		);
	}
}
