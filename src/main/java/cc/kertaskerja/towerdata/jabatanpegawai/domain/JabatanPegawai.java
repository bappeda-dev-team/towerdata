package cc.kertaskerja.towerdata.jabatanpegawai.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

public record JabatanPegawai(
		@Id
		Long id,
		
		@Column("nip_pegawai")
		String nipPegawai,
		
		@Column("kode_jabatan")
		String kodeJabatan,
		
		@Column("kode_pemda")
		String kodePemda,
		
		@CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate
) {
	public static JabatanPegawai of(
			String nipPegawai,
			String kodeJabatan,
			String kodePemda
	) {
		return new JabatanPegawai(
				null, 
				nipPegawai, 
				kodeJabatan, 
				kodePemda, 
				null, 
				null
		);
	}
}
