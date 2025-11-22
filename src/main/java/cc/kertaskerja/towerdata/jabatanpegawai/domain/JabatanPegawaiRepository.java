package cc.kertaskerja.towerdata.jabatanpegawai.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface JabatanPegawaiRepository extends CrudRepository<JabatanPegawai, Long> {
	@NonNull
    Optional<JabatanPegawai> findById(@NonNull Long id);
	
	@NonNull
    Page<JabatanPegawai> findByNipPegawaiContainingIgnoreCaseAndKodeJabatanContainingIgnoreCase(
            @NonNull String nipPegawai,
            @NonNull String kodeJabatan,
            @NonNull Pageable pageable
    );
	
	@NonNull
    Page<JabatanPegawai> findAll(@NonNull Pageable pageable);
	
	boolean existsById(@NonNull Long id);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM jabatan_pegawai WHERE id = :id")
    void deleteById(@NonNull @Param("id") Long id);
}
