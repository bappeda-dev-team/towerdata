package cc.kertaskerja.towerdata.subkegiatan.web;

public record SubkegiatanSearchResponse(
        String kodeSubKegiatan,
        String namaSubKegiatan,
        Boolean penunjang
) {
}