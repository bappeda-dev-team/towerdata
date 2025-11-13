package cc.kertaskerja.towerdata.bidangurusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.Opd;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;

@Service
public class BidangUrusanService {
	private final BidangUrusanRepository bidangUrusanRepository;
	private final OpdRepository opdRepository;
	
	public BidangUrusanService(BidangUrusanRepository bidangUrusanRepository, OpdRepository opdRepository) {
		this.bidangUrusanRepository = bidangUrusanRepository;
		this.opdRepository = opdRepository;
	}
	
	public Page<BidangUrusan> cariBidangUrusan(String kodeBidangUrusan, String namaBidangUrusan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bidangUrusanRepository.findByKodeBidangUrusanContainingIgnoreCaseAndNamaBidangUrusanContainingIgnoreCase(
                kodeBidangUrusan, namaBidangUrusan, pageable
        );
    }
	
	public Page<BidangUrusan> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return bidangUrusanRepository.findAll(pageable);
        } else {
            return bidangUrusanRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }
	
	public BidangUrusan detailBidangUrusan(Long id) {
        return bidangUrusanRepository.findById(id)
                .orElseThrow(() -> new BidangUrusanNotFoundException(id));
    }

    public Page<BidangUrusan> getBidangUrusanByOpdId(Long opdId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bidangUrusanRepository.findByOpdId(opdId, pageable);
    }

    public Page<BidangUrusan> getBidangUrusanByOpdIdAndPenunjang(Long opdId, Boolean penunjang, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bidangUrusanRepository.findByOpdIdAndPenunjang(opdId, penunjang, pageable);
    }
	
	public BidangUrusan tambahBidangUrusan(BidangUrusan bidangUrusan) {
		if (bidangUrusan.opdId() != null) {
			if (!opdRepository.existsById(bidangUrusan.opdId())) {
				throw new OpdNotFoundException(bidangUrusan.opdId());
			}
		}
        return bidangUrusanRepository.save(bidangUrusan);
    }

    public BidangUrusan ubahBidangUrusan(Long id, BidangUrusan bidangUrusan) {
        if (!bidangUrusanRepository.existsById(id)) {
            throw new BidangUrusanNotFoundException(id);
        }

        if (bidangUrusan.opdId() != null) {
			if (!opdRepository.existsById(bidangUrusan.opdId())) {
				throw new OpdNotFoundException(bidangUrusan.opdId());
			}
		}

        return bidangUrusanRepository.save(bidangUrusan);
    }

	public void hapusBidangUrusan(Long id) {
        if (!bidangUrusanRepository.existsById(id)) {
            throw new BidangUrusanNotFoundException(id);
        }

        bidangUrusanRepository.deleteById(id);
    }
}
