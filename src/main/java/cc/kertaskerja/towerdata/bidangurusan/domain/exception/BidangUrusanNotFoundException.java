package cc.kertaskerja.towerdata.bidangurusan.domain.exception;

public class BidangUrusanNotFoundException extends RuntimeException {
	public BidangUrusanNotFoundException(String kodeBidangUrusan) {
		super("Bidang urusan dengan kode " + kodeBidangUrusan + " tidak ditemukan.");
	}
}
