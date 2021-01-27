package com.mauricio.SpringAPI.Repositories;

import com.mauricio.SpringAPI.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
