package cc.kertaskerja.towerdata.urusan.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface UrusanRepository extends CrudRepository<Urusan, Long> {
	@NonNull
    Optional<Urusan> findById(@NonNull Long id);
	
	@NonNull
    Page<Urusan> findByKodeUrusanContainingIgnoreCaseAndNamaUrusanContainingIgnoreCase(
            @NonNull String kodeUrusan,
            @NonNull String namaUrusan,
            @NonNull Pageable pageable
    );
	
	@NonNull
    Page<Urusan> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<Urusan> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM urusan WHERE id = :id")
    void deleteById(@NonNull Long id);
}
