package cc.kertaskerja.towerdata.pegawai.web.response;

import jakarta.annotation.Nullable;

public record PegawaiResponse(
        String nipPegawai,
        String namaPegawai,
        Boolean penunjang,
        @Nullable
        String kodeOpd,
        @Nullable
        String namaOpd,
        String namaRolePegawai
) {
}
