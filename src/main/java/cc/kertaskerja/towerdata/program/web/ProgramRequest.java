package cc.kertaskerja.towerdata.program.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProgramRequest(
        @Nullable
        Long programId,

        @NotNull(message = "Kode Program tidak boleh kosong")
        @NotEmpty(message = "Kode Program tidak boleh kosong")
        String kodeProgram,

        @NotNull(message = "Nama Program tidak boleh kosong")
        @NotEmpty(message = "Nama Program tidak boleh kosong")
        String namaProgram,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
