package cc.kertaskerja.towerdata.bidangurusan.domain.exception;

public class BidangUrusanNotFoundException extends RuntimeException {
	public BidangUrusanNotFoundException(Long id) {
		super("Bidang urusan dengan id " + id + " tidak ditemukan.");
	}
}
