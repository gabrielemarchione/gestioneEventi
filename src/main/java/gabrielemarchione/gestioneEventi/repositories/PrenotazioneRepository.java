package gabrielemarchione.gestioneEventi.repositories;

import gabrielemarchione.gestioneEventi.entities.Evento;
import gabrielemarchione.gestioneEventi.entities.Prenotazione;
import gabrielemarchione.gestioneEventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByUtenteAndEvento(Utente utente, Evento evento);
}