package cc.kertaskerja.towerdata.user.web;

import cc.kertaskerja.towerdata.user.domain.User;
import cc.kertaskerja.towerdata.user.domain.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("detail/cari")
    public List<UserSearchResponse> search(
            @RequestParam(value = "kode", required = false) String nipPegawai,
            @RequestParam(value = "nama", required = false) String namaRoleUser,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<User> users = userService.cariUser(
                nipPegawai != null ? nipPegawai : "",
                namaRoleUser != null ? namaRoleUser : "",
                page,
                size
        );

        return users.stream()
                .map(urusan -> new UserSearchResponse(
                        urusan.nipPegawai(),
                        urusan.namaRoleUser()
                ))
                .toList();
    }

    @GetMapping("detail/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.detailUser(id);
    }

    @PutMapping("update/{id}")
    public User put(@PathVariable("id") Long id, @Valid @RequestBody UserRequest request) {
        // Ambil data user yang sudah dibuat
        User existingUser = userService.detailUser(id);

        User user = new User(
                id,
                request.kodeOpd(),
                request.nipPegawai(),
                request.emailUser(),
                request.statusUser(),
                request.levelRoleUser(),
                request.namaRoleUser(),
                existingUser.createdDate(),
                null
        );

        return userService.ubahUser(id, user);
    }

    @PostMapping
    public ResponseEntity<User> post(@Valid @RequestBody UserRequest request) {
        User user = User.of(
                request.kodeOpd(),
                request.nipPegawai(),
                request.emailUser(),
                request.statusUser(),
                request.levelRoleUser(),
                request.namaRoleUser()
        );
        User saved = userService.tambahUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.hapusUser(id);
    }
}
