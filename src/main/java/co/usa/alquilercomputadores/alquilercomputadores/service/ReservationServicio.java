package co.usa.alquilercomputadores.alquilercomputadores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.alquilercomputadores.alquilercomputadores.model.Reservation;
import co.usa.alquilercomputadores.alquilercomputadores.repository.ReservationRepositorio;

@Service
public class ReservationServicio {
    @Autowired
    private ReservationRepositorio reservationRepositorio;

    public  List<Reservation> getAll(){
        return  reservationRepositorio.getAll();
    }

    public Optional<Reservation>getReservation(int id){
        return reservationRepositorio.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if (reservation.getIdReservation()==null) {
            return reservationRepositorio.save(reservation);
        } else {
            Optional<Reservation>  consulta=reservationRepositorio.getReservation(reservation.getIdReservation());
            if (consulta.isEmpty()) {
                return  reservationRepositorio.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepositorio.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepositorio.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepositorio.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
