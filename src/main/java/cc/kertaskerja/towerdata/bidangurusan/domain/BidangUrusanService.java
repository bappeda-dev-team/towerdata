package cc.kertaskerja.towerdata.bidangurusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cc.kertaskerja.towerdata.bidangurusan.domain.exception.BidangUrusanNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.bidangurusan.web.BidangUrusanSearchResponse;

import java.time.Duration;
import java.util.List;

@Service
public class BidangUrusanService {
	private final BidangUrusanRepository bidangUrusanRepository;
	private final OpdRepository opdRepository;
    private final WebClient towerdataClient;
	
	public BidangUrusanService(
            BidangUrusanRepository bidangUrusanRepository,
            OpdRepository opdRepository,
            WebClient towerdataClient
    ) {
		this.bidangUrusanRepository = bidangUrusanRepository;
		this.opdRepository = opdRepository;
        this.towerdataClient = towerdataClient;
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

    public List<BidangUrusanSearchResponse> findAllFromApi() {
        return towerdataClient.get()
                .uri("/bidangurusan/detail/findall")
                .retrieve()
                .bodyToFlux(BidangUrusanSearchResponse.class)
                .timeout(Duration.ofSeconds(5))
                .collectList()
                .block();
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
