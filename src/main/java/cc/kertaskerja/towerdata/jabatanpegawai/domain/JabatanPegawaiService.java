package cc.kertaskerja.towerdata.jabatanpegawai.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.jabatanpegawai.domain.exception.JabatanPegawaiNotFoundException;

@Service
public class JabatanPegawaiService {
	private final JabatanPegawaiRepository jabatanPegawaiRepository;
	
	public JabatanPegawaiService(JabatanPegawaiRepository jabatanPegawaiRepository) {
		this.jabatanPegawaiRepository = jabatanPegawaiRepository;
	}
	
	public Page<JabatanPegawai> cariJabatanPegawai(String nipPegawai, String kodeJabatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jabatanPegawaiRepository.findByNipPegawaiContainingIgnoreCaseAndKodeJabatanContainingIgnoreCase(
                nipPegawai, kodeJabatan, pageable
        );
    }
	
	public JabatanPegawai detailJabatanPegawai(Long id) {
        return jabatanPegawaiRepository.findById(id)
                .orElseThrow(() -> new JabatanPegawaiNotFoundException(id));
    }
	
	public List<JabatanPegawai> semuaJabatanPegawai() {
        return jabatanPegawaiRepository.findAll(Pageable.unpaged()).getContent();
    }
	
	public JabatanPegawai tambahJabatanPegawai(JabatanPegawai jabatanPegawai) {
        return jabatanPegawaiRepository.save(jabatanPegawai);
    }
	
	public JabatanPegawai ubahJabatanPegawai(Long id, JabatanPegawai jabatanPegawai) {
        if (!jabatanPegawaiRepository.existsById(id)) {
            throw new JabatanPegawaiNotFoundException(id);
        }

        return jabatanPegawaiRepository.save(jabatanPegawai);
    }
	
	public void hapusJabatanPegawai(Long id) {
        if (!jabatanPegawaiRepository.existsById(id)) {
            throw new JabatanPegawaiNotFoundException(id);
        }

        jabatanPegawaiRepository.deleteById(id);
    }
}
