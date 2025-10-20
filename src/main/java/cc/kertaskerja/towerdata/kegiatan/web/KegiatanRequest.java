package cc.kertaskerja.towerdata.kegiatan.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record KegiatanRequest(
        @Nullable
        Long kegiatanId,

        @NotNull(message = "Kode Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Kode Kegiatan tidak boleh kosong")
        String kodeKegiatan,

        @NotNull(message = "Nama Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Nama Kegiatan tidak boleh kosong")
        String namaKegiatan,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
