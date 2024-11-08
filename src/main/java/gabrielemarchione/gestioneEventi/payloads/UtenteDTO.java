package gabrielemarchione.gestioneEventi.payloads;

import gabrielemarchione.gestioneEventi.enums.Ruolo;
import jakarta.validation.constraints.*;
public record UtenteDTO(@NotEmpty(message = "Il nome è obbligatorio!")
                        @Size(min = 2, max = 20, message = "Il nome deve contentere dai 2 ai 20 caratteri")
                        String nome,
                        @NotEmpty(message = "Il cognome è obbligatorio!")
                        @Size(min = 2, max = 20, message = "Il nome deve contentere dai 2 ai 20 caratteri")
                        String cognome,
                        @NotEmpty(message = "L'email è obbligatoria!")
                        @Email(message = "L'email non è un indirizzo corretto!")
                        String email,
                        @NotEmpty(message = "Il cognome è obbligatorio!")
                        @Size(min = 2, max = 20, message = "Il nome deve contentere dai 2 ai 20 caratteri")
                        String username,
                        @NotEmpty(message = "Il campo password non può essere vuoto!")
                        @NotNull
                        @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.*\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$", message = "La password non rispetta i criteri!")
                        String password,
                        @NotNull
                        @Pattern(regexp = "IN_PROGRAMMA | TERMINATO")
                        Ruolo ruolo) {
}