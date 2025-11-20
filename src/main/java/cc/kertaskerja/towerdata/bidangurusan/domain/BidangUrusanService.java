package cc.kertaskerja.towerdata.bidangurusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;

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
	
	public Iterable<BidangUrusan> getAllBidangUrusan() {
		return bidangUrusanRepository.findAll();
	}
	
	public BidangUrusan detailBidangUrusan(String kodeBidangUrusan) {
        return bidangUrusanRepository.findByKodeBidangUrusan(kodeBidangUrusan)
                .orElseThrow(() -> new BidangUrusanNotFoundException(kodeBidangUrusan));
    }
	
	public BidangUrusan tambahBidangUrusan(BidangUrusan bidangUrusan) {
        return bidangUrusanRepository.save(bidangUrusan);
    }

    public BidangUrusan ubahBidangUrusan(String kodeBidangUrusan, BidangUrusan bidangUrusan) {
        if (!bidangUrusanRepository.existsByKodeBidangUrusan(kodeBidangUrusan)) {
            throw new BidangUrusanNotFoundException(kodeBidangUrusan);
        }

        return bidangUrusanRepository.save(bidangUrusan);
    }

	public void hapusBidangUrusan(String kodeBidangUrusan) {
        if (!bidangUrusanRepository.existsByKodeBidangUrusan(kodeBidangUrusan)) {
            throw new BidangUrusanNotFoundException(kodeBidangUrusan);
        }

        bidangUrusanRepository.deleteByKodeBidangUrusan(kodeBidangUrusan);
    }
}
