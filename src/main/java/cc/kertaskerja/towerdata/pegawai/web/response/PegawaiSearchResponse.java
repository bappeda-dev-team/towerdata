package cc.kertaskerja.towerdata.pegawai.web.response;

import jakarta.annotation.Nullable;

public record PegawaiSearchResponse(
        String nipPegawai,
        String namaPegawai,
        @Nullable
        String kodeOpd,
        boolean aktif,
        boolean khusus
) {
}
