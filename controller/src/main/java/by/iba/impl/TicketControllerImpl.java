package by.iba.impl;

import by.iba.TicketController;
import by.iba.TicketService;
import by.iba.dto.req.ticket.TicketReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.ticket.TicketDTO;
import by.iba.dto.resp.ticket.TicketsDTO;
import by.iba.helper.ControllerHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;

    public ResponseEntity<TicketDTO> createTicket(@RequestBody @Valid TicketReqDTO ticketReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        TicketDTO ticket = ticketService.save(ticketReqDTO);

        return ResponseEntity
                .status(200)
                .body(ticket);
    }

    @Override
    public ResponseEntity<TicketsDTO> getTickets() {
        List<TicketDTO> tickets = ticketService.getAll();

        return ResponseEntity
                .ok()
                .body(new TicketsDTO(tickets));
    }

    @Override
    public ResponseEntity<RespStatusDTO> deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("Car is successfully deleted"));
    }

}
