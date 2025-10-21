package cc.kertaskerja.towerdata.pegawai.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PegawaiRepository extends CrudRepository<Pegawai, Long> {
    @NonNull
    Optional<Pegawai> findById(@NonNull Long id);

    @NonNull
    Page<Pegawai> findByKodePegawaiContainingIgnoreCaseAndNamaPegawaiContainingIgnoreCase(
            @NonNull String kodePegawai,
            @NonNull String namaPegawai,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Pegawai> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<Pegawai> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM pegawai WHERE id = :id")
    void deleteById(@NonNull Long id);
}
