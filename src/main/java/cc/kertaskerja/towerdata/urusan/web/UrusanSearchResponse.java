package cc.kertaskerja.towerdata.urusan.web;

public record UrusanSearchResponse (
		String kodeUrusan,
        String namaUrusan,
        Boolean penunjang
) {
}
