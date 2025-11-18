package cc.kertaskerja.towerdata.pegawai.domain;

import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PegawaiService {
    private PegawaiRepository pegawaiRepository;
    private OpdRepository opdRepository;

    public PegawaiService(PegawaiRepository pegawaiRepository, OpdRepository opdRepository) {
        this.pegawaiRepository = pegawaiRepository;
        this.opdRepository = opdRepository;
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

    public Pegawai tambahPegawai(Pegawai pegawai) {
        // Validasi OPD jika kodeOpd tidak null
        if (pegawai.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(pegawai.kodeOpd())) {
                throw new OpdNotFoundException(pegawai.kodeOpd());
            }
        }

        return pegawaiRepository.save(pegawai);
    }

    public Pegawai ubahPegawai(String nipPegawai, Pegawai pegawai) {
        if (!pegawaiRepository.existsByNipPegawai(nipPegawai)) {
            throw new PegawaiNotFoundException(nipPegawai);
        }

        // Validasi OPD jika kodeOpd tidak null
        if (pegawai.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(pegawai.kodeOpd())) {
                throw new OpdNotFoundException(pegawai.kodeOpd());
            }
        }

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
