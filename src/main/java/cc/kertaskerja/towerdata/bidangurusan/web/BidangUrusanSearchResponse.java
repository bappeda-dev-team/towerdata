package cc.kertaskerja.towerdata.bidangurusan.web;

public record BidangUrusanSearchResponse(
		String kodeBidangUrusan,
		String namaBidangUrusan,
		Boolean penunjang,
		Long opdId
) {
}
