package cc.kertaskerja.towerdata.jabatan.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.jabatan.domain.exception.JabatanNotFoundException;
import cc.kertaskerja.towerdata.jabatan.web.JabatanRequest;

@Service
public class JabatanService {
    private final JabatanRepository jabatanRepository;

    public JabatanService(JabatanRepository jabatanRepository) {
        this.jabatanRepository = jabatanRepository;
    }

    public Page<Jabatan> cariJabatan(String kodeJabatan, String namaJabatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jabatanRepository.findByKodeJabatanContainingIgnoreCaseAndNamaJabatanContainingIgnoreCase(
                kodeJabatan, namaJabatan, pageable
        );
    }

    public Jabatan detailJabatan(String kodeJabatan) {
        return jabatanRepository.findByKodeJabatan(kodeJabatan)
                .orElseThrow(() -> new JabatanNotFoundException(kodeJabatan));
    }

    public List<Jabatan> semuaJabatan() {
        return jabatanRepository.findAll(Pageable.unpaged()).getContent();
    }

    public Jabatan tambahJabatan(JabatanRequest request) {
        Jabatan jabatan = Jabatan.of(
                request.kodeJabatan(),
                request.namaJabatan(),
                request.struktural()
        );

        return jabatanRepository.save(jabatan);
    }

    public Jabatan ubahJabatan(String kodeJabatan, JabatanRequest request) {
        Jabatan existingJabatan = detailJabatan(kodeJabatan);

        Jabatan jabatan = new Jabatan(
                existingJabatan.id(),
                request.kodeJabatan(),
                request.namaJabatan(),
                request.struktural(),
                existingJabatan.createdDate(),
                null
        );

        return jabatanRepository.save(jabatan);
    }

    public void hapusJabatan(String kodeJabatan) {
        if (!jabatanRepository.existsByKodeJabatan(kodeJabatan)) {
            throw new JabatanNotFoundException(kodeJabatan);
        }

        jabatanRepository.deleteByKodeJabatan(kodeJabatan);
    }
}
