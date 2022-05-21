package by.iba;

import by.iba.dto.req.ticket.TicketReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.ticket.TicketDTO;
import by.iba.dto.resp.ticket.TicketsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/autopicker/tickets")
@CrossOrigin(origins = "*")
public interface TicketController {

    @PostMapping(value = "/create")
    ResponseEntity<TicketDTO> createTicket(@RequestBody @Valid TicketReqDTO ticketReqDTO, BindingResult bindingResult);

    @GetMapping("/")
    ResponseEntity<TicketsDTO> getTickets();

    @DeleteMapping("/{id}")
    ResponseEntity<RespStatusDTO> deleteTicket(@PathVariable Long id);


}
