package by.iba.mapper;

import by.iba.dto.req.ticket.TicketReqDTO;
import by.iba.dto.resp.ticket.TicketDTO;
import by.iba.entity.car.*;
import by.iba.entity.request.Request;
import by.iba.entity.ticket.Ticket;
import by.iba.entity.user.UserEntity;
import by.iba.exception.ResourceNotFoundException;
import by.iba.repository.CarRepository;
import by.iba.repository.RequestRepository;
import by.iba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final CarMapper carMapper;
    private final UserMapper userMapper;

    public Ticket fillFromRespDTO(TicketReqDTO ticketReqDTO){
        Ticket ticket = new Ticket();

        if (Objects.nonNull(ticketReqDTO.getName())) {
            ticket.setName(ticketReqDTO.getName());
        }

        if (Objects.nonNull(ticketReqDTO.getDescription())) {
            ticket.setDescription(ticketReqDTO.getDescription());
        }

        if (Objects.nonNull(ticketReqDTO.getDateOfDeparture())) {
            ticket.setDateOfDeparture(LocalDate.parse(ticketReqDTO.getDateOfDeparture()));
        }

        if (Objects.nonNull(ticketReqDTO.getCarId())) {
            Car car = carRepository.findById(ticketReqDTO.getCarId())
                    .orElseThrow(() -> new ResourceNotFoundException("CAR_HAS_BEEN_NOT_FOUND"));
            ticket.setCar(car);
        }

        if (Objects.nonNull(ticketReqDTO.getUserId())) {
            UserEntity autopicker = userRepository.findById(ticketReqDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("USER_HAS_BEEN_NOT_FOUND"));
            ticket.setAutoPicker(autopicker);
        }

        if (Objects.nonNull(ticketReqDTO.getUserId())) {
            Request request = requestRepository.findById(ticketReqDTO.getRequestId())
                    .orElseThrow(() -> new ResourceNotFoundException("REQUEST_HAS_BEEN_NOT_FOUND"));
            ticket.setRequest(request);
        }


        return ticket;
    }

    public TicketDTO fillFromInDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();

        if(Objects.nonNull(ticket.getId())) {
            ticketDTO.setId(ticket.getId());
        }

        if(Objects.nonNull(ticket.getDateOfCreation())) {
            ticketDTO.setDateOfCreation(ticket.getDateOfCreation());
        }

        if(Objects.nonNull(ticket.getDateOfLastUpdate())) {
            ticketDTO.setDateOfLastUpdate(ticket.getDateOfLastUpdate());
        }

        if(Objects.nonNull(ticket.getName())) {
            ticketDTO.setName(ticket.getName());
        }

        if(Objects.nonNull(ticket.getDescription())) {
            ticketDTO.setDescription(ticket.getDescription());
        }

        if(Objects.nonNull(ticket.getDateOfDeparture())) {
            ticketDTO.setDateOfDeparture(ticket.getDateOfDeparture());
        }

        if(Objects.nonNull(ticket.getCar())) {
            ticketDTO.setCar(carMapper.fillFromInDTO(ticket.getCar()));
        }

        if(Objects.nonNull(ticket.getAutoPicker())) {
            ticketDTO.setAutoPicker(userMapper.fillFromInDTO(ticket.getAutoPicker()));
        }

        return ticketDTO;
    }

    public TicketDTO fillRequestInDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();

        if(Objects.nonNull(ticket.getId())) {
            ticketDTO.setId(ticket.getId());
        }

        if(Objects.nonNull(ticket.getDateOfCreation())) {
            ticketDTO.setDateOfCreation(ticket.getDateOfCreation());
        }

        if(Objects.nonNull(ticket.getDateOfLastUpdate())) {
            ticketDTO.setDateOfLastUpdate(ticket.getDateOfLastUpdate());
        }

        if(Objects.nonNull(ticket.getName())) {
            ticketDTO.setName(ticket.getName());
        }

        if(Objects.nonNull(ticket.getDescription())) {
            ticketDTO.setDescription(ticket.getDescription());
        }

        if(Objects.nonNull(ticket.getDateOfDeparture())) {
            ticketDTO.setDateOfDeparture(ticket.getDateOfDeparture());
        }

        if(Objects.nonNull(ticket.getCar())) {
            ticketDTO.setCar(carMapper.fillFromInDTO(ticket.getCar()));
        }

        if(Objects.nonNull(ticket.getAutoPicker())) {
            ticketDTO.setAutoPicker(userMapper.fillFromInDTO(ticket.getAutoPicker()));
        }

        return ticketDTO;
    }

}
