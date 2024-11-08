package gabrielemarchione.gestioneEventi.repositories;

import gabrielemarchione.gestioneEventi.entities.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    Page<Evento> findByOrganizzatore(UUID id_utente, Pageable pageable);
}
