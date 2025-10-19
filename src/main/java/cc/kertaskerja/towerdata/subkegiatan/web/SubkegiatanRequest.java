package cc.kertaskerja.towerdata.subkegiatan.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubkegiatanRequest(
        @Nullable
        Long subKegiatanId,

        @NotNull(message = "Kode Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Kode Sub Kegiatan tidak boleh kosong")
        String kodeSubKegiatan,

        @NotNull(message = "Nama Sub Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Nama Sub Kegiatan tidak boleh kosong")
        String namaSubKegiatan,

        @NotNull(message = "Status tidak boleh kosong")
        @NotEmpty(message = "Status tidak boleh kosong")
        String status,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
