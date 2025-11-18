package cc.kertaskerja.towerdata.user.domain;

import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import cc.kertaskerja.towerdata.pegawai.domain.PegawaiRepository;
import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import cc.kertaskerja.towerdata.user.domain.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private OpdRepository opdRepository;
    private PegawaiRepository pegawaiRepository;

    public UserService(UserRepository userRepository, OpdRepository opdRepository, PegawaiRepository pegawaiRepository) {
        this.userRepository = userRepository;
        this.opdRepository = opdRepository;
        this.pegawaiRepository = pegawaiRepository;
    }

    public Page<User> cariUser(String nipPegawai, String namaRoleUser, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByNipPegawaiContainingIgnoreCaseAndNamaRoleUserContainingIgnoreCase(
                nipPegawai, namaRoleUser, pageable
        );
    }

    public User detailUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User tambahUser(User user) {
        if (user.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(user.kodeOpd())) {
                throw new OpdNotFoundException(user.kodeOpd());
            }
        }

        if (user.nipPegawai() != null) {
            if (!pegawaiRepository.existsByNipPegawai(user.nipPegawai())) {
                throw new PegawaiNotFoundException(user.nipPegawai());
            }
        }

        return userRepository.save(user);
    }

    public User ubahUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        if (user.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(user.kodeOpd())) {
                throw new OpdNotFoundException(user.kodeOpd());
            }
        }

        if (user.nipPegawai() != null) {
            if (!pegawaiRepository.existsByNipPegawai(user.nipPegawai())) {
                throw new PegawaiNotFoundException(user.nipPegawai());
            }
        }

        return userRepository.save(user);
    }

    public void hapusUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }
}
