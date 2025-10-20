package cc.kertaskerja.towerdata.urusan.domain.exception;

public class UrusanAlreadyExistException extends RuntimeException{
	public UrusanAlreadyExistException(Long id) {
		super("Urusan dengan id " + id + " sudah ada.");
	}
}
