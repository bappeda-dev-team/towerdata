package cc.kertaskerja.towerdata.kegiatan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface KegiatanRepository extends CrudRepository<Kegiatan, Long> {
    @NonNull
    Optional<Kegiatan> findById(@NonNull Long id);

    @NonNull
    Optional<Kegiatan> findByKodeKegiatan(@NonNull String kodeKegiatan);

    boolean existsByKodeKegiatan(@NonNull String kodeKegiatan);

    void deleteByKodeKegiatan(@NonNull String kodeKegiatan);

    @NonNull
    Page<Kegiatan> findByKodeKegiatanContainingIgnoreCaseAndNamaKegiatanContainingIgnoreCase(
            @NonNull String kodeKegiatan,
            @NonNull String namaKegiatan,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Kegiatan> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM kegiatan WHERE kode_program = :kode_program")
    void deleteByKodeProgram(@NonNull String kodeProgram);
}
