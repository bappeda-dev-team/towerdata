package cc.kertaskerja.towerdata.urusan.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UrusanRequest(
		@Nullable
        Long urusanId,

        @NotNull(message = "Kode Urusan tidak boleh kosong")
        @NotEmpty(message = "Kode Urusan tidak boleh kosong")
        String kodeUrusan,

        @NotNull(message = "Nama Urusan tidak boleh kosong")
        @NotEmpty(message = "Nama Urusan tidak boleh kosong")
        String namaUrusan,

        @NotNull(message = "Kode Pemda tidak boleh kosong")
        @NotEmpty(message = "Kode Pemda tidak boleh kosong")
        String kodePemda,

        @NotNull(message = "Penunjang tidak boleh kosong")
        Boolean penunjang
) {
}
