package cc.kertaskerja.towerdata.subkegiatan.web;

import jakarta.validation.constraints.NotEmpty;

public record SubkegiatanBatchRequest(
        @NotEmpty(message = "Kode Sub Kegiatan tidak boleh kosong")
        String kodeSubKegiatan,

        @NotEmpty(message = "Nama Sub Kegiatan tidak boleh kosong")
        String namaSubKegiatan
) {
}