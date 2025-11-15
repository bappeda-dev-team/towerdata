package cc.kertaskerja.towerdata.subkegiatan.web.response;

import jakarta.annotation.Nullable;

public record SubkegiatanSearchResponse(
        @Nullable
        String kodeOpd,
        String kodeSubKegiatan,
        String namaSubKegiatan,
        Boolean penunjang
) {
}