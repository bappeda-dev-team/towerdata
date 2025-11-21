package cc.kertaskerja.towerdata.jabatan.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record JabatanRequest(
        @Nullable
        Long jabatanId,

        @NotNull(message = "Kode Jabatan tidak boleh kosong")
        @NotEmpty(message = "Kode Jabatan tidak boleh kosong")
        String kodeJabatan,

        @NotNull(message = "Nama Jabatan tidak boleh kosong")
        @NotEmpty(message = "Nama Jabatan tidak boleh kosong")
        String namaJabatan,

        @NotNull(message = "Struktural tidak boleh kosong")
        Boolean struktural
) {
}
