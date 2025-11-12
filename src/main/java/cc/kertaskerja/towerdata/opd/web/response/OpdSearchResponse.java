package cc.kertaskerja.towerdata.opd.web.response;

public record OpdSearchResponse(
        String kodeOpd,
        String namaOpd,
        Boolean penunjang
) {
}
