package cc.kertaskerja.towerdata.subkegiatan.web.response;

import jakarta.annotation.Nullable;

public record SubkegiatanSearchResponse(
        @Nullable
        String kodeSubKegiatan,
        String namaSubKegiatan
) {
}