package cc.kertaskerja.towerdata.jabatan.web;

public record JabatanSearchResponse(
        String kodeJabatan,
		String namaJabatan,
		Boolean penunjang
) {
}
