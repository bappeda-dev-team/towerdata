package cc.kertaskerja.towerdata.urusan.domain.exception;

public class UrusanNotFoundException extends RuntimeException {
	public UrusanNotFoundException(Long id) {
		super("Urusan dengan id " + id + " tidak ditemukan.");
	}
}
