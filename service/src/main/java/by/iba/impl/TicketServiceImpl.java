package by.iba.impl;

import by.iba.TicketService;
import by.iba.dto.req.ticket.TicketReqDTO;
import by.iba.dto.resp.ticket.TicketDTO;
import by.iba.entity.ticket.Ticket;
import by.iba.mapper.TicketMapper;
import by.iba.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    
    @Override
    @Transactional
    public TicketDTO save(TicketReqDTO ticketReqDTO) {

        Ticket ticket = ticketMapper.fillFromRespDTO(ticketReqDTO);

        ticket = ticketRepository.save(ticket);

        return ticketMapper.fillFromInDTO(ticket);
    }

    @Override
    @Transactional
    public List<TicketDTO> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::fillFromInDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
    
}
