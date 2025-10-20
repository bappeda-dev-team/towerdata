package cc.kertaskerja.towerdata.program.domain.exception;

public class ProgramAlreadyExistException extends RuntimeException {
    public ProgramAlreadyExistException(Long id) {
        super("Program dengan id " + id + " sudah ada.");
    }
}
