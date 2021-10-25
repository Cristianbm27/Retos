package co.usa.alquilercomputadores.alquilercomputadores.repository.crud;

import org.springframework.data.repository.CrudRepository;

import co.usa.alquilercomputadores.alquilercomputadores.model.Reservation;

public interface ReservationCrudRepositorio extends  CrudRepository<Reservation,Integer>{
    
}
