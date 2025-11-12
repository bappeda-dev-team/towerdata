package cc.kertaskerja.towerdata.pegawai.web.response;

import jakarta.annotation.Nullable;

public record PegawaiSearchResponse(
        String kodePegawai,
        String namaPegawai,
        Boolean penunjang,
        @Nullable
        Long opdId,
        String namaRolePegawai
) {
}
