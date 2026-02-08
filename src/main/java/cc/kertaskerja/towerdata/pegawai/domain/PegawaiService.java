package cc.kertaskerja.towerdata.pegawai.domain;

import cc.kertaskerja.towerdata.jabatan.domain.JabatanRepository;
import cc.kertaskerja.towerdata.jabatan.domain.exception.JabatanNotFoundException;
import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import cc.kertaskerja.towerdata.pegawai.web.PegawaiRequest;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PegawaiService {
    private PegawaiRepository pegawaiRepository;
    private OpdRepository opdRepository;
    private JabatanRepository jabatanRepository;

    public PegawaiService(PegawaiRepository pegawaiRepository, OpdRepository opdRepository, JabatanRepository jabatanRepository) {
        this.pegawaiRepository = pegawaiRepository;
        this.opdRepository = opdRepository;
        this.jabatanRepository = jabatanRepository;
    }

    public Page<Pegawai> cariPegawai(String nipPegawai, String namaPegawai, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pegawaiRepository.findByNipPegawaiContainingIgnoreCaseAndNamaPegawaiContainingIgnoreCase(
                nipPegawai, namaPegawai, pageable
        );
    }
    
    public List<Pegawai> getPegawaiByNipPegawai(String nipPegawai) {
        return pegawaiRepository.findByNipPegawai(nipPegawai);
    }

    public Pegawai detailPegawai(String nipPegawai) {
        return pegawaiRepository.findByNipPegawai(nipPegawai).stream()
                .findFirst()
                .orElseThrow(() -> new PegawaiNotFoundException(nipPegawai));
    }
    
    public List<Pegawai> findAll() {
        return StreamSupport.stream(pegawaiRepository.findAll().spliterator(), false)
                .toList();
    }

    public Pegawai tambahPegawai(PegawaiRequest request) {
        if (request.kodeOpd() != null && !opdRepository.existsByKodeOpd(request.kodeOpd())) {
            throw new OpdNotFoundException(request.kodeOpd());
        }

        if (request.kodeJabatan() != null && !jabatanRepository.existsByKodeJabatan(request.kodeJabatan())) {
            throw new JabatanNotFoundException(request.kodeJabatan());
        }

        Pegawai pegawai = Pegawai.of(
                request.nipPegawai(),
                request.namaPegawai(),
                request.kodeOpd(),
                request.kodeJabatan(),
                request.aktif() != null ? request.aktif() : false,
                request.khusus() != null ? request.khusus() : false
        );

        return pegawaiRepository.save(pegawai);
    }

    public Pegawai ubahPegawai(String nipPegawai, PegawaiRequest request) {
        Pegawai existingPegawai = detailPegawai(nipPegawai);

        if (request.kodeOpd() != null && !opdRepository.existsByKodeOpd(request.kodeOpd())) {
            throw new OpdNotFoundException(request.kodeOpd());
        }

        if (request.kodeJabatan() != null && !jabatanRepository.existsByKodeJabatan(request.kodeJabatan())) {
            throw new JabatanNotFoundException(request.kodeJabatan());
        }

        boolean aktif = request.aktif() != null ? request.aktif() : existingPegawai.aktif();
        boolean khusus = request.khusus() != null ? request.khusus() : existingPegawai.khusus();

        Pegawai pegawai = new Pegawai(
                existingPegawai.id(),
                request.nipPegawai(),
                request.namaPegawai(),
                request.kodeOpd(),
                request.kodeJabatan(),
                aktif,
                khusus,
                existingPegawai.createdDate(),
                null
        );

        return pegawaiRepository.save(pegawai);
    }

    public Page<Pegawai> getPegawaiByKodeOpd(String kodeOpd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pegawaiRepository.findByKodeOpd(kodeOpd, pageable);
    }

    public void hapusPegawai(String nipPegawai) {
        if (!pegawaiRepository.existsByNipPegawai(nipPegawai)) {
            throw new PegawaiNotFoundException(nipPegawai);
        }

        pegawaiRepository.deleteByNipPegawai(nipPegawai);
    }
}
