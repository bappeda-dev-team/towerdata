package cc.kertaskerja.towerdata.kegiatan.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record KegiatanRequest(

        @NotNull(message = "Kode Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Kode Kegiatan tidak boleh kosong")
        String kodeKegiatan,

        @NotNull(message = "Nama Kegiatan tidak boleh kosong")
        @NotEmpty(message = "Nama Kegiatan tidak boleh kosong")
        String namaKegiatan
) {
}
