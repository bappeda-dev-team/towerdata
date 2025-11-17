package cc.kertaskerja.towerdata.pemda.domain;

import cc.kertaskerja.towerdata.pemda.domain.exception.PemdaNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PemdaService {
    private PemdaRepository pemdaRepository;

    public PemdaService(PemdaRepository pemdaRepository) {
        this.pemdaRepository = pemdaRepository;
    }

    public Page<Pemda> cariPemda(String kodePemda, String namaPemda, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pemdaRepository.findByKodePemdaContainingIgnoreCaseAndNamaPemdaContainingIgnoreCase(
                kodePemda, namaPemda, pageable
        );
    }

    public Pemda detailPemdaByKodePemda(String kodePemda) {
        return pemdaRepository.findByKodePemda(kodePemda)
                .orElseThrow(() -> new PemdaNotFoundException(kodePemda));
    }

    public Pemda tambahPemda(Pemda pemda) {
        return pemdaRepository.save(pemda);
    }

    public Pemda ubahPemda(String kodePemda, Pemda pemda) {
        if (!pemdaRepository.existsByKodePemda(kodePemda)) {
            throw new PemdaNotFoundException(kodePemda);
        }

        return pemdaRepository.save(pemda);
    }

    public void hapusPemda(String kodePemda) {
        if (!pemdaRepository.existsByKodePemda(kodePemda)) {
            throw new PemdaNotFoundException(kodePemda);
        }

        pemdaRepository.deleteByKodePemda(kodePemda);
    }
}
