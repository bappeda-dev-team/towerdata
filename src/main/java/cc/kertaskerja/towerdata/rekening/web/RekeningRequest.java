package cc.kertaskerja.towerdata.rekening.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RekeningRequest(

        @NotNull(message = "Kode Rekening tidak boleh kosong")
        @NotEmpty(message = "Kode Rekening tidak boleh kosong")
        String kodeRekening,

        @NotNull(message = "Nama Rekening tidak boleh kosong")
        @NotEmpty(message = "Nama Rekening tidak boleh kosong")
        String namaRekening
) {
}
