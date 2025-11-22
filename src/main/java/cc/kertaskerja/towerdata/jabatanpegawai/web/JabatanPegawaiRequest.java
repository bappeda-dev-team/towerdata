package cc.kertaskerja.towerdata.jabatanpegawai.web;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record JabatanPegawaiRequest(
		@Nullable
		Long idJabatanPegawai,
		
		@NotNull(message = "NIP pegawai tidak boleh kosong")
        @NotEmpty(message = "NIP pegawai tidak boleh kosong")
        String nipPegawai,
        
        @NotNull(message = "Kode Jabatan tidak boleh kosong")
        @NotEmpty(message = "Kode Jabatan tidak boleh kosong")
        String kodeJabatan,
        
        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda
) {
}
