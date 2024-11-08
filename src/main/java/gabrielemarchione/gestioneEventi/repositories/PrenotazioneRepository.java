package gabrielemarchione.gestioneEventi.repositories;

import gabrielemarchione.gestioneEventi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
}
