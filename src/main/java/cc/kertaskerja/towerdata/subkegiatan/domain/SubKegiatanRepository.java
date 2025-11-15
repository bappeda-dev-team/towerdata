package cc.kertaskerja.towerdata.subkegiatan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SubKegiatanRepository extends CrudRepository<SubKegiatan, Long> {
    @NonNull
    Optional<SubKegiatan> findById(@NonNull Long id);

    @NonNull
    Page<SubKegiatan> findByKodeSubKegiatanContainingIgnoreCaseAndNamaSubKegiatanContainingIgnoreCase(
            @NonNull String kodeSubKegiatan,
            @NonNull String namaSubKegiatan,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<SubKegiatan> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<SubKegiatan> findAll(@NonNull Pageable pageable);

    List<SubKegiatan> findByKodeOpd(@NonNull String kodeOpd);

    @Modifying
    @Transactional
    @Query("DELETE FROM subkegiatan WHERE id = :id")
    void deleteById(@NonNull Long id);
}
