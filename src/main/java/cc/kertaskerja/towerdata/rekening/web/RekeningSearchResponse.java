package cc.kertaskerja.towerdata.rekening.web;

public record RekeningSearchResponse(
        String kodeRekening,
        String namaRekening,
        Boolean aktif
) {
}
