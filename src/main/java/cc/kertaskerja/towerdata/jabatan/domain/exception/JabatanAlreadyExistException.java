package cc.kertaskerja.towerdata.jabatan.domain.exception;

public class JabatanAlreadyExistException extends RuntimeException{
    public JabatanAlreadyExistException(Long id) {
        super("Jabatan dengan id " + id + " sudah ada.");
    }
}
