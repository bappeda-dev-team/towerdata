package cc.kertaskerja.towerdata.program.domain;

import cc.kertaskerja.towerdata.kegiatan.domain.Kegiatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProgramRepository extends CrudRepository<Program, Long> {
    @NonNull
    Optional<Program> findById(@NonNull Long id);

    @NonNull
    Page<Program> findByKodeProgramContainingIgnoreCaseAndNamaProgramContainingIgnoreCase(
            @NonNull String kodeProgram,
            @NonNull String namaProgram,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Program> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<Program> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM program WHERE id = :id")
    void deleteById(@NonNull Long id);
}
