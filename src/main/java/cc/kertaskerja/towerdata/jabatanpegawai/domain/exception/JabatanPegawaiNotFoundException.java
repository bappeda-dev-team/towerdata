package cc.kertaskerja.towerdata.jabatanpegawai.domain.exception;

public class JabatanPegawaiNotFoundException extends RuntimeException {
	public JabatanPegawaiNotFoundException(Long id) {
		super("Jabatan pegawai dengan id " + id + " tidak ditemukan.");
	}
}
