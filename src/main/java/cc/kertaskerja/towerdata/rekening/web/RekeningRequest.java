package cc.kertaskerja.towerdata.rekening.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RekeningRequest(
        @Nullable
        Long rekeningId,

        @NotNull(message = "Kode Rekening tidak boleh kosong")
        @NotEmpty(message = "Kode Rekening tidak boleh kosong")
        String kodeRekening,

        @NotNull(message = "Nama Rekening tidak boleh kosong")
        @NotEmpty(message = "Nama Rekening tidak boleh kosong")
        String namaRekening,

        @NotNull(message = "Aktif tidak boleh kosong")
        Boolean aktif
) {
}
