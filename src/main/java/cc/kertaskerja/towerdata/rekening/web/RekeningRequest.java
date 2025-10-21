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

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
