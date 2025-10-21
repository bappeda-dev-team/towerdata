package cc.kertaskerja.towerdata.pegawai.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PegawaiRequest(
        @Nullable
        Long opdId,

        @NotNull(message = "Kode Pegawai tidak boleh kosong")
        @NotEmpty(message = "Kode Pegawai tidak boleh kosong")
        String kodePegawai,

        @NotNull(message = "Nama Pegawai tidak boleh kosong")
        @NotEmpty(message = "Nama Pegawai tidak boleh kosong")
        String namaPegawai,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
