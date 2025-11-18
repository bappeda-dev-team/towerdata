package cc.kertaskerja.towerdata.pegawai.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PegawaiRepository extends CrudRepository<Pegawai, Long> {
    @NonNull
    Optional<Pegawai> findById(@NonNull Long id);

    @NonNull
    Page<Pegawai> findByNipPegawaiContainingIgnoreCaseAndNamaPegawaiContainingIgnoreCase(
            @NonNull String nipPegawai,
            @NonNull String namaPegawai,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<Pegawai> findAll(@NonNull Pageable pageable);

    @NonNull
    Page<Pegawai> findByKodeOpd(@NonNull String kodeOpd, @NonNull Pageable pageable);

    boolean existsByNipPegawai(@NonNull String nipPegawai);
    
    List<Pegawai> findByNipPegawai(String nipPegawai);

    @Modifying
    @Transactional
    @Query("DELETE FROM pegawai WHERE nip_pegawai = :nipPegawai")
    void deleteByNipPegawai(@NonNull String nipPegawai);

}
