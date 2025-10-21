package cc.kertaskerja.towerdata.rekening.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RekeningRepository extends CrudRepository<Rekening, Long> {
    @NonNull
    Optional<Rekening> findById(@NonNull Long id);

    @NonNull
    Page<Rekening> findByKodeRekeningContainingIgnoreCaseAndNamaRekeningContainingIgnoreCase(
            @NonNull String kodeRekening,
            @NonNull String namaRekening,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Rekening> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<Rekening> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM rekening WHERE id = :id")
    void deleteById(@NonNull Long id);
}
