package cc.kertaskerja.towerdata.pegawai.web;

public record PegawaiSearchResponse(
        String kodePegawai,
        String namaPegawai,
        Boolean penunjang
) {
}
