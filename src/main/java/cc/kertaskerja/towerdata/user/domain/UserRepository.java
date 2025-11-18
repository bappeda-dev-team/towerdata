package cc.kertaskerja.towerdata.user.domain;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @NotNull
    Optional<User> findById(@NotNull Long id);

    @NonNull
    Page<User> findByNipPegawaiContainingIgnoreCaseAndNamaRoleUserContainingIgnoreCase(
            @NonNull String nipPegawai,
            @NonNull String namaRoleUser,
            @NonNull Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM \"user\" WHERE id = :id")
    void deleteById(@NonNull Long id);
}
