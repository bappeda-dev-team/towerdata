package cc.kertaskerja.towerdata.program.domain.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(Long id) {
        super("Program dengan id " + id + " tidak ditemukan.");
    }
}
