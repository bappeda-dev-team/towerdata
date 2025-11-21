package cc.kertaskerja.towerdata.jabatan.domain.exception;

public class JabatanAlreadyExistException extends RuntimeException{
    public JabatanAlreadyExistException(String kodeJabatan) {
        super("Jabatan dengan kode " + kodeJabatan + " sudah ada.");
    }
}
