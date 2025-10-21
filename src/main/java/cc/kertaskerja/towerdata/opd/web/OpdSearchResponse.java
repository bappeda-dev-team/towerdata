package cc.kertaskerja.towerdata.opd.web;

public record OpdSearchResponse(
        String kodeJabatan,
        String namaJabatan,
        Boolean penunjang
) {
}
