package cc.kertaskerja.towerdata.pemda.domain;

import cc.kertaskerja.towerdata.pemda.domain.exception.PemdaNotFoundException;
import cc.kertaskerja.towerdata.pemda.web.PemdaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PemdaService {
    private PemdaRepository pemdaRepository;

    public PemdaService(PemdaRepository pemdaRepository) {
        this.pemdaRepository = pemdaRepository;
    }

    public List<Pemda> findAll() {
        return StreamSupport.stream(pemdaRepository.findAll().spliterator(), false)
                .toList();
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

    public Pemda tambahPemda(PemdaRequest request) {
        Pemda pemda = Pemda.of(
                request.kodePemda(),
                request.namaPemda()
        );

        return pemdaRepository.save(pemda);
    }

    public Pemda ubahPemda(String kodePemda, PemdaRequest request) {
        Pemda existingPemda = detailPemdaByKodePemda(kodePemda);

        Pemda pemda = new Pemda(
                existingPemda.id(),
                request.kodePemda(),
                request.namaPemda(),
                existingPemda.createdDate(),
                null
        );

        return pemdaRepository.save(pemda);
    }

    public void hapusPemda(String kodePemda) {
        if (!pemdaRepository.existsByKodePemda(kodePemda)) {
            throw new PemdaNotFoundException(kodePemda);
        }

        pemdaRepository.deleteByKodePemda(kodePemda);
    }
}
