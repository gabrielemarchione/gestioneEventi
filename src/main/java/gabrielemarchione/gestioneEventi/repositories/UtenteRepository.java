package gabrielemarchione.gestioneEventi.repositories;


import gabrielemarchione.gestioneEventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    boolean existsByEmail(String email);
}