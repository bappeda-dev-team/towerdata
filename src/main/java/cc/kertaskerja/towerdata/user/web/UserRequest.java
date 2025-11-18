package cc.kertaskerja.towerdata.user.web;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
		@Nullable
        String kodeOpd,

        @Nullable
        String nipPegawai,

        @NotNull(message = "Email User tidak boleh kosong")
        @NotEmpty(message = "Email User tidak boleh kosong")
        String emailUser,

        @NotNull(message = "Status User tidak boleh kosong")
        @NotEmpty(message = "Status User tidak boleh kosong")
        String statusUser,

        @NotNull(message = "Level role user tidak boleh kosong")
        @NotEmpty(message = "Level role user tidak boleh kosong")
        String levelRoleUser,

        @NotNull(message = "Nama role user tidak boleh kosong")
        @NotEmpty(message = "Nama role user tidak boleh kosong")
        String namaRoleUser
) {
}
