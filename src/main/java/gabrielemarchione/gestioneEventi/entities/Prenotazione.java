package gabrielemarchione.gestioneEventi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_prenotazione;

    @Setter(AccessLevel.NONE)
    private LocalDate data_prenotazione;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    @JsonManagedReference
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    @JsonManagedReference
    private Evento evento;

    public Prenotazione(Utente utente, Evento evento) {
        this.data_prenotazione = LocalDate.now();
        this.utente = utente;
        this.evento = evento;
    }
}