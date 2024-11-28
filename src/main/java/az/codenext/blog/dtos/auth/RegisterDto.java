package az.codenext.blog.dtos.auth;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email formati duzgun deyil.")
    private String email;
    @NotNull
    @Size(min = 3, max = 20, message = "Ad minimum 3 maksimum 20 simvoldan ibaret ola biler")
    private String name;
    @Size(min = 3, max = 20, message = "Soyad minimum 3 maksimum 20 simvoldan ibaret ola biler")
    private String surname;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@!%*?&]{8,}$",message = "Sifre cox sadedir.")
    private String password;
    @NotNull(message = "Bu hisses bos ola bilmez")
    private String passwordRepeat;


    @AssertTrue(message = "Sifreler uygun deyil.")
    public boolean isPasswordsMatching(){
        return password != null && password.equals(passwordRepeat);
    }

}
