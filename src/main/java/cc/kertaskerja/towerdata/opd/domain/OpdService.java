package cc.kertaskerja.towerdata.opd.domain;

import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OpdService {
    private OpdRepository opdRepository;

    public OpdService(OpdRepository opdRepository) {
        this.opdRepository = opdRepository;
    }

    public Page<Opd> cariOpd(String kodeOpd, String namaOpd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return opdRepository.findByKodeOpdContainingIgnoreCaseAndNamaOpdContainingIgnoreCase(
                kodeOpd, namaOpd, pageable
        );
    }

    public Page<Opd> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return opdRepository.findAll(pageable);
        } else {
            return opdRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Opd detailOpd(Long id) {
        return opdRepository.findById(id)
                .orElseThrow(() -> new OpdNotFoundException(id));
    }

    public Opd tambahOpd(Opd opd) {
        return opdRepository.save(opd);
    }

    public Opd ubahOpd(Long id, Opd opd) {
        if (!opdRepository.existsById(id)) {
            throw new OpdNotFoundException(id);
        }

        return opdRepository.save(opd);
    }

    public void hapusOpd(Long id) {
        if (!opdRepository.existsById(id)) {
            throw new OpdNotFoundException(id);
        }

        opdRepository.deleteById(id);
    }
}
