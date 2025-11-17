package cc.kertaskerja.towerdata.pemda.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PemdaRequest(
        @Nullable
        Long pemdaId,

        @NotNull(message = "Id Pemda tidak boleh kosong")
        @NotEmpty(message = "Id Pemda tidak boleh kosong")
        String idPemda,

        @NotNull(message = "kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Nama Pemda tidak boleh kosong")
        @NotEmpty(message = "Nama Pemda tidak boleh kosong")
        String namaPemda
) {
}
