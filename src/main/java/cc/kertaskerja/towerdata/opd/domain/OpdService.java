package cc.kertaskerja.towerdata.opd.domain;

import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import cc.kertaskerja.towerdata.opd.web.OpdRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OpdService {
    private final OpdRepository opdRepository;

    public OpdService(OpdRepository opdRepository) {
        this.opdRepository = opdRepository;
    }

    public Page<Opd> cariOpd(String kodeOpd, String namaOpd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return opdRepository.findByKodeOpdContainingIgnoreCaseAndNamaOpdContainingIgnoreCase(
                kodeOpd, namaOpd, pageable
        );
    }

    public List<Opd> findAll() {
        return StreamSupport.stream(opdRepository.findAll().spliterator(), false)
                .toList();
    }

    public Opd detailOpdByKodeOpd(String kodeOpd) {
        return opdRepository.findByKodeOpd(kodeOpd)
                .orElseThrow(() -> new OpdNotFoundException(kodeOpd));
    }

    public Opd tambahOpd(OpdRequest request) {
        Opd opd = Opd.of(
                request.kodeOpd(),
                request.namaOpd()
        );

        return opdRepository.save(opd);
    }

    public Opd ubahOpd(String kodeOpd, OpdRequest request) {
        Opd existingOpd = detailOpdByKodeOpd(kodeOpd);

        Opd opd = new Opd(
                existingOpd.id(),
                request.kodeOpd(),
                request.namaOpd(),
                existingOpd.createdDate(),
                null
        );

        return opdRepository.save(opd);
    }

    public void hapusOpd(String kodeOpd) {
        if (!opdRepository.existsByKodeOpd(kodeOpd)) {
            throw new OpdNotFoundException(kodeOpd);
        }

        opdRepository.deleteByKodeOpd(kodeOpd);
    }
}
