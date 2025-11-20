package cc.kertaskerja.towerdata.bidangurusan.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BidangUrusanRequest(
		@Nullable
        Long bidangUrusanId,

        @NotNull(message = "Kode Bidang Urusan tidak boleh kosong")
        @NotEmpty(message = "Kode Bidang Urusan tidak boleh kosong")
        String kodeBidangUrusan,

        @NotNull(message = "Nama Bidang Urusan tidak boleh kosong")
        @NotEmpty(message = "Nama Bidang Urusan tidak boleh kosong")
        String namaBidangUrusan
) {
}
