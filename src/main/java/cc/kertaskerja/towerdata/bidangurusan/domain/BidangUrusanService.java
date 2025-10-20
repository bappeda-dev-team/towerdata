package cc.kertaskerja.towerdata.bidangurusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanNotFoundException;

@Service
public class BidangUrusanService {
	private final BidangUrusanRepository bidangUrusanRepository;
	
	public BidangUrusanService(BidangUrusanRepository bidangUrusanRepository) {
		this.bidangUrusanRepository = bidangUrusanRepository;
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
	
	public BidangUrusan tambahBidangUrusan(BidangUrusan bidangUrusan) {
        return bidangUrusanRepository.save(bidangUrusan);
    }

    public BidangUrusan ubahBidangUrusan(Long id, BidangUrusan bidangUrusan) {
        if (!bidangUrusanRepository.existsById(id)) {
            throw new BidangUrusanNotFoundException(id);
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
