package cc.kertaskerja.towerdata.kegiatan.domain;

import cc.kertaskerja.towerdata.kegiatan.domain.exception.KegiatanNotFoundException;
import cc.kertaskerja.towerdata.kegiatan.web.KegiatanRequest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KegiatanService {
    private final KegiatanRepository kegiatanRepository;

    public KegiatanService(KegiatanRepository kegiatanRepository) {
        this.kegiatanRepository = kegiatanRepository;
    }
    
    public Page<Kegiatan> cariKegiatan(String kodeKegiatan, String namaKegiatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return kegiatanRepository.findByKodeKegiatanContainingIgnoreCaseAndNamaKegiatanContainingIgnoreCase(
                kodeKegiatan, namaKegiatan, pageable
        );
    }
    
    public List<Kegiatan> semuaKegiatan() {
        return kegiatanRepository.findAll(Pageable.unpaged()).getContent();
    }

    public Kegiatan detailKegiatan(String kodeKegiatan) {
        return kegiatanRepository.findByKodeKegiatan(kodeKegiatan)
                .orElseThrow(() -> new KegiatanNotFoundException(kodeKegiatan));
    }

    public Kegiatan tambahKegiatan(KegiatanRequest request) {
        Kegiatan kegiatan = Kegiatan.of(
                request.kodeKegiatan(),
                request.namaKegiatan()
        );

        return kegiatanRepository.save(kegiatan);
    }

    public Kegiatan ubahKegiatan(String kodeKegiatan, KegiatanRequest request) {
        Kegiatan existingKegiatan = detailKegiatan(kodeKegiatan);

        Kegiatan kegiatan = new Kegiatan(
                existingKegiatan.id(),
                request.kodeKegiatan(),
                request.namaKegiatan(),
                existingKegiatan.createdDate(),
                null
        );

        return kegiatanRepository.save(kegiatan);
    }

    public void hapusKegiatan(String kodeKegiatan) {
        if (!kegiatanRepository.existsByKodeKegiatan(kodeKegiatan)) {
            throw new KegiatanNotFoundException(kodeKegiatan);
        }

        kegiatanRepository.deleteByKodeKegiatan(kodeKegiatan);
    }
}
