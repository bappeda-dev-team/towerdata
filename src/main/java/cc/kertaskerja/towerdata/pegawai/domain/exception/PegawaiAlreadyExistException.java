package cc.kertaskerja.towerdata.pegawai.domain.exception;

public class PegawaiAlreadyExistException extends RuntimeException {
    public PegawaiAlreadyExistException(String nipPegawai) {
        super("Pegawai dengan nip " + nipPegawai + " sudah ada.");
    }
}
