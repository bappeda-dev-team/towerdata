package cc.kertaskerja.towerdata.subkegiatan.web.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubkegiatanRequest(

        @NotNull(message = "Kode Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Kode Sub Kegiatan tidak boleh kosong")
        String kodeSubKegiatan,

        @NotNull(message = "Nama Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Nama Sub Kegiatan tidak boleh kosong")
        String namaSubKegiatan
) {
}
