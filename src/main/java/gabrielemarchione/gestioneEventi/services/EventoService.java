package gabrielemarchione.gestioneEventi.services;

import gabrielemarchione.gestioneEventi.entities.Evento;
import gabrielemarchione.gestioneEventi.exceptions.NotFoundException;
import gabrielemarchione.gestioneEventi.payloads.EventoDTO;
import gabrielemarchione.gestioneEventi.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Page<Evento> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventoRepository.findAll(pageable);
    }

    public Evento findById(UUID id_evento) {
        return this.eventoRepository.findById(id_evento).orElseThrow(() -> new NotFoundException(id_evento));
    }

    public Evento saveEvento(EventoDTO payload, UUID id_utente) {
        Evento newEvento = new Evento(payload.titolo(), payload.descrizione(), payload.data_evento(), payload.luogo(), payload.posti_disponibili());
        return this.eventoRepository.save(newEvento);
    }

    public Evento findByIdAndUpdate(UUID id_evento, EventoDTO payload) {
        Evento evento = this.findById(id_evento);
        evento.setTitolo(payload.titolo());
        evento.setDescrizione(payload.descrizione());
        evento.setData_evento(payload.data_evento());
        evento.setLuogo(payload.luogo());
        evento.setPosti_disponibili(payload.posti_disponibili());
        return this.eventoRepository.save(evento);
    }

    public void deleteEvento(UUID id_evento) {
        Evento evento = this.findById(id_evento);
        this.eventoRepository.delete(evento);
    }

    public Page<Evento> findAllByOrganizzatore(UUID organizzatore, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoRepository.findByOrganizzatore(organizzatore, pageable);
    }
}