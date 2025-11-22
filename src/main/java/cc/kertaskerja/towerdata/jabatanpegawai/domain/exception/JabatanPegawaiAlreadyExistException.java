package cc.kertaskerja.towerdata.jabatanpegawai.domain.exception;

public class JabatanPegawaiAlreadyExistException extends RuntimeException {
	public JabatanPegawaiAlreadyExistException(Long id) {
		super("Jabatan pegawai dengan id " + id + " sudah ada.");
	}
}
