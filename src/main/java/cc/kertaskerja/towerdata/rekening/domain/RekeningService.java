package cc.kertaskerja.towerdata.rekening.domain;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.rekening.domain.exception.RekeningNotFoundException;
import cc.kertaskerja.towerdata.rekening.web.RekeningRequest;

@Service
public class RekeningService {
    private final RekeningRepository rekeningRepository;

    public RekeningService(RekeningRepository rekeningRepository) {
        this.rekeningRepository = rekeningRepository;
    }

    public Page<Rekening> cariRekening(String kodeRekening, String namaRekening, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rekeningRepository.findByKodeRekeningContainingIgnoreCaseAndNamaRekeningContainingIgnoreCase(
                kodeRekening, namaRekening, pageable
        );
    }

    public Rekening detailRekening(String kodeRekening) {
        return rekeningRepository.findByKodeRekening(kodeRekening)
                .orElseThrow(() -> new RekeningNotFoundException(kodeRekening));
    }

    public List<Rekening> semuaRekening() {
        return StreamSupport.stream(rekeningRepository.findAll().spliterator(), false)
                .toList();
    }

    public Rekening tambahRekening(RekeningRequest request) {
        Rekening rekening = Rekening.of(
                request.kodeRekening(),
                request.namaRekening()
        );

        return rekeningRepository.save(rekening);
    }

    public Rekening ubahRekening(String kodeRekening, RekeningRequest request) {
        Rekening existingRekening = detailRekening(kodeRekening);

        Rekening rekeningToSave = new Rekening(
                existingRekening.id(),
                request.kodeRekening(),
                request.namaRekening(),
                existingRekening.createdDate(),
                null
        );

        return rekeningRepository.save(rekeningToSave);
    }

    public void hapusRekening(String kodeRekening) {
        if (!rekeningRepository.existsByKodeRekening(kodeRekening)) {
            throw new RekeningNotFoundException(kodeRekening);
        }

        rekeningRepository.deleteByKodeRekening(kodeRekening);
    }
}
