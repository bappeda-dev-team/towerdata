package cc.kertaskerja.towerdata.pegawai.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PegawaiRequest(
        @Nullable
        String kodeOpd,

        @Nullable
        String kodeJabatan,

        @NotNull(message = "NIP Pegawai tidak boleh kosong")
        @NotEmpty(message = "NIP Pegawai tidak boleh kosong")
        String nipPegawai,

        @NotNull(message = "Nama Pegawai tidak boleh kosong")
        @NotEmpty(message = "Nama Pegawai tidak boleh kosong")
        String namaPegawai
) {
}
