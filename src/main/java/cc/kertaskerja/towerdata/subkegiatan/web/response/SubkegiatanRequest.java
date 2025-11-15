package cc.kertaskerja.towerdata.subkegiatan.web.response;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubkegiatanRequest(
        @Nullable
        Long subKegiatanId,

        @Nullable
        String kodeOpd,

        @NotNull(message = "Kode Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Kode Sub Kegiatan tidak boleh kosong")
        String kodeSubKegiatan,

        @NotNull(message = "Nama Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Nama Sub Kegiatan tidak boleh kosong")
        String namaSubKegiatan,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
