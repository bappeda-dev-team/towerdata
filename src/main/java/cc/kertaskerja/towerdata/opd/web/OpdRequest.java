package cc.kertaskerja.towerdata.opd.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OpdRequest(
        @Nullable
        Long opdId,

        @NotNull(message = "Kode Opd tidak boleh kosong")
        @NotEmpty(message = "Kode Opd tidak boleh kosong")
        String kodeOpd,

        @NotNull(message = "Nama Opd tidak boleh kosong")
        @NotEmpty(message = "Nama Opd tidak boleh kosong")
        String namaOpd,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
