package cc.kertaskerja.towerdata.rekening.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.rekening.domain.exception.RekeningNotFoundException;

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

    public Page<Rekening> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return rekeningRepository.findAll(pageable);
        } else {
            return rekeningRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Rekening detailRekening(Long id) {
        return rekeningRepository.findById(id)
                .orElseThrow(() -> new RekeningNotFoundException(id));
    }

    public Rekening tambahRekening(Rekening rekening) {
        return rekeningRepository.save(rekening);
    }

    public Rekening ubahRekening(Long id, Rekening rekening) {
        if (!rekeningRepository.existsById(id)) {
            throw new RekeningNotFoundException(id);
        }

        return rekeningRepository.save(rekening);
    }

    public void hapusRekening(Long id) {
        if (!rekeningRepository.existsById(id)) {
            throw new RekeningNotFoundException(id);
        }

        rekeningRepository.deleteById(id);
    }
}
