package gabrielemarchione.gestioneEventi.services;

import gabrielemarchione.gestioneEventi.entities.Evento;
import gabrielemarchione.gestioneEventi.entities.Prenotazione;
import gabrielemarchione.gestioneEventi.entities.Utente;
import gabrielemarchione.gestioneEventi.exceptions.NotFoundException;
import gabrielemarchione.gestioneEventi.payloads.PrenotazioneDTO;
import gabrielemarchione.gestioneEventi.repositories.PrenotazioneRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    @Autowired
    UtenteService utenteService;

    @Autowired
    EventoService eventoService;

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(UUID id_prenotazione) {
        return this.prenotazioneRepository.findById(id_prenotazione).orElseThrow(() -> new NotFoundException(id_prenotazione));
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO payload, UUID id_utente) throws BadRequestException {
        Evento evento = this.eventoService.findById(UUID.fromString(payload.id_evento()));
        Utente utente = this.utenteService.findById(id_utente);
        if (this.prenotazioneRepository.existsByUtenteAndEvento(utente, evento)) {
            throw new BadRequestException("Hai già una prenotazione per questo evento.");
        }
        Prenotazione newPrenotazione = new Prenotazione(utente, evento);
        return this.prenotazioneRepository.save(newPrenotazione);
    }

    public void deletePrenotazione(UUID id_prenotazione) {
        Prenotazione prenotazione = this.findById(id_prenotazione);
        this.prenotazioneRepository.delete(prenotazione);
    }

}
