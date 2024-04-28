package com.example.crud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.crud.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{


    

    
}
