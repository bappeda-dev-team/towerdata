package cc.kertaskerja.towerdata.pemda.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PemdaRepository extends CrudRepository<Pemda, Long> {
    @NonNull
    Optional<Pemda> findById(@NonNull Long id);

    @NonNull
    Page<Pemda> findByKodePemdaContainingIgnoreCaseAndNamaPemdaContainingIgnoreCase(
            @NonNull String kodePemda,
            @NonNull String namaPemda,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Pemda> findAll(@NonNull Pageable pageable);

    boolean existsByKodePemda(@NonNull String kodePemda);

    @NonNull
    Optional<Pemda> findByKodePemda(@NonNull String kodePemda);

    @Modifying
    @Transactional
    @Query("DELETE FROM pemda WHERE id = :id")
    void deleteByKodePemda(@NonNull String kodePemda);
}
