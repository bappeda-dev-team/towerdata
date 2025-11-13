package cc.kertaskerja.towerdata.bidangurusan.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface BidangUrusanRepository extends CrudRepository<BidangUrusan, Long> {
	@NonNull
    Optional<BidangUrusan> findById(@NonNull Long id);
    
    @NonNull
    Page<BidangUrusan> findByKodeBidangUrusanContainingIgnoreCaseAndNamaBidangUrusanContainingIgnoreCase(
            @NonNull String kodeBidangUrusan,
            @NonNull String namaBidangUrusan,
            @NonNull Pageable pageable
    );
    
    @NonNull
    Page<BidangUrusan> findByPenunjang(@NonNull Boolean penunjang, @NonNull Pageable pageable);

    @NonNull
    Page<BidangUrusan> findAll(@NonNull Pageable pageable);

    @NonNull
    Page<BidangUrusan> findByOpdId(@NonNull Long opdId, @NonNull Pageable pageable);

    @NonNull
    Page<BidangUrusan> findByOpdIdAndPenunjang(@NonNull Long opdId, @NonNull Boolean penunjang, @NonNull Pageable pageable);

    boolean existsByOpdId(@NonNull Long opdId);

    @Modifying
    @Transactional
    @Query("DELETE FROM bidang_urusan WHERE id = :id")
    void deleteById(@NonNull Long id);
}
