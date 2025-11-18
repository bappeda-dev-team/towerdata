package cc.kertaskerja.towerdata.user.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(Long id) {
        super("User dengan id " + id + " sudah ada.");
    }
}
