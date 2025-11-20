package cc.kertaskerja.towerdata.bidangurusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BidangUrusanRepository extends CrudRepository<BidangUrusan, Long> {
	@NonNull
    Optional<BidangUrusan> findById(@NonNull Long id);

    boolean existsByKodeBidangUrusan(@NonNull String kodeBidangUrusan);

    @NonNull
    Optional<BidangUrusan> findByKodeBidangUrusan(@NonNull String kodeBidangUrusan);
    
    @NonNull
    Page<BidangUrusan> findByKodeBidangUrusanContainingIgnoreCaseAndNamaBidangUrusanContainingIgnoreCase(
            @NonNull String kodeBidangUrusan,
            @NonNull String namaBidangUrusan,
            @NonNull Pageable pageable
    );

    @NonNull
    Page<BidangUrusan> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM bidang_urusan WHERE kode_bidang_urusan = :kodeBidangUrusan")
    void deleteByKodeBidangUrusan(@NonNull @Param("kodeBidangUrusan") String kodeBidangUrusan);
}
