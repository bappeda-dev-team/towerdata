package cc.kertaskerja.towerdata.bidangurusan.domain.exception;

public class BidangUrusanAlreadyExistException extends RuntimeException {
	public BidangUrusanAlreadyExistException(Long id) {
		super("Bidang urusan dengan id " + id + " sudah ada.");
	}
}
