package by.iba;

import by.iba.dto.req.ticket.TicketReqDTO;
import by.iba.dto.resp.ticket.TicketDTO;

import java.util.List;

public interface TicketService {

    TicketDTO save(TicketReqDTO ticketReqDTO);

    List<TicketDTO> getAll();

    void deleteById(Long id);

}