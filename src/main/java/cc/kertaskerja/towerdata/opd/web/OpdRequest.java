package cc.kertaskerja.towerdata.opd.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OpdRequest(
        @NotNull(message = "Kode Opd tidak boleh kosong")
        @NotEmpty(message = "Kode Opd tidak boleh kosong")
        String kodeOpd,

        @NotNull(message = "Nama Opd tidak boleh kosong")
        @NotEmpty(message = "Nama Opd tidak boleh kosong")
        String namaOpd
) {
}
