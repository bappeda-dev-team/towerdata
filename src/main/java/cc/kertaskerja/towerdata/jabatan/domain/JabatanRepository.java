package cc.kertaskerja.towerdata.jabatan.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface JabatanRepository extends CrudRepository<Jabatan, Long> {
    @NonNull
    Optional<Jabatan> findById(@NonNull Long id);

    @NonNull
    Optional<Jabatan> findByKodeJabatan(@NonNull String kodeJabatan);

    @NonNull
    Page<Jabatan> findByKodeJabatanContainingIgnoreCaseAndNamaJabatanContainingIgnoreCase(
            @NonNull String kodeJabatan,
            @NonNull String namaJabatan,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Jabatan> findAll(@NonNull Pageable pageable);

    boolean existsByKodeJabatan(@NonNull String kodeJabatan);

    @Modifying
    @Transactional
    @Query("DELETE FROM jabatan WHERE kode_jabatan = :kodeJabatan")
    void deleteByKodeJabatan(@NonNull @Param("kodeJabatan") String kodeJabatan);
}
