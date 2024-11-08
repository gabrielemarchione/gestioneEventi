package gabrielemarchione.gestioneEventi.services;

import gabrielemarchione.gestioneEventi.entities.Prenotazione;
import gabrielemarchione.gestioneEventi.entities.Utente;
import gabrielemarchione.gestioneEventi.exceptions.NotFoundException;
import gabrielemarchione.gestioneEventi.payloads.UtenteDTO;
import gabrielemarchione.gestioneEventi.repositories.UtenteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }
    public Utente findById(UUID id_utente) {
        return this.utenteRepository.findById(id_utente).orElseThrow(() -> new NotFoundException(id_utente));
    }
    public Utente saveUtente(UtenteDTO payload) throws BadRequestException {
        if (this.utenteRepository.existsByEmail(payload.email()))
            throw new BadRequestException("La mail è già in uso");
        Utente newUtente = new Utente(payload.nome(), payload.cognome(), payload.email(), payload.username(), payload.password(), payload.ruolo());
        return this.utenteRepository.save(newUtente);
    }
    public Utente findByIdAndUpdate(UUID id_utente, UtenteDTO payload) throws BadRequestException {
        Utente utente = this.findById(id_utente);
        if (!utente.getEmail().equals(payload.email())) {
            if (this.utenteRepository.existsByEmail(payload.email()))
                throw new BadRequestException("La mail è già in uso");
        }
        utente.setNome(payload.nome());
        utente.setCognome(payload.cognome());
        utente.setEmail(payload.email());
        utente.setUsername(payload.username());
        utente.setPassword(payload.password());
        return this.utenteRepository.save(utente);
    }
    public void deleteUtente(UUID id_utente) {
        Utente utente = this.findById(id_utente);
        this.utenteRepository.delete(utente);
    }
    public List<Prenotazione> findPrenotazioniByUtenteId(UUID idUtente) {
        Utente utente = this.findById(idUtente);
        return utente.getPrenotazioni();
    }
}
