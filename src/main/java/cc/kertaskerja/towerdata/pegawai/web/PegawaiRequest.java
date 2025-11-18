package cc.kertaskerja.towerdata.pegawai.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PegawaiRequest(
        @Nullable
        String kodeOpd,

        @NotNull(message = "NIP Pegawai tidak boleh kosong")
        @NotEmpty(message = "NIP Pegawai tidak boleh kosong")
        String nipPegawai,

        @NotNull(message = "Nama Pegawai tidak boleh kosong")
        @NotEmpty(message = "Nama Pegawai tidak boleh kosong")
        String namaPegawai,

        @NotNull(message = "Kode Jabatan tidak boleh kosong")
        @NotEmpty(message = "Kode Jabatan tidak boleh kosong")
        String kodeJabatan,

        @NotNull(message = "Nama Jabatan tidak boleh kosong")
        @NotEmpty(message = "Nama Jabatan tidak boleh kosong")
        String namaJabatan
) {
}
