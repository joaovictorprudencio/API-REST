package com.example.crud.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.models.Cliente;
import com.example.crud.repositorio.ClienteRepository;
import com.example.crud.service.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class clientes {

   @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

@PostMapping("/cliente")
public ResponseEntity <Cliente> SalvarCliente(@RequestBody Cliente cliente){  
Cliente novoCliente = clienteService.SalvarCliente(cliente);
return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
}

@GetMapping("/cliente/{id}")
public ResponseEntity<Cliente> EsseCliente(@PathVariable Long id) {
       ResponseEntity<Cliente> response;
       Optional<Cliente> clienteOptional = clienteService.BuscarPorId(id);
       if(clienteOptional.isPresent()){
        response = new ResponseEntity<>(clienteOptional.get(), HttpStatus.OK);
       } else{
        response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return response;
}
   
   @PutMapping("/cliente/{id}")
   public ResponseEntity<Optional<Cliente>> AtualizarCl(@PathVariable Long id, @RequestBody Cliente novoCliente) {
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       if (clienteOptional.isPresent()) {

           Optional<Cliente> cliente = clienteService.AtualizarCliente(id, novoCliente);

           return ResponseEntity.ok(cliente);

       } else {

           return ResponseEntity.notFound().build();

       }
   
     
}


@DeleteMapping("/cliente/{id}")
public ResponseEntity<Void> Delet(@PathVariable Long id ){
       clienteService.DeletarCliente(id);
       return ResponseEntity.notFound().build();
    }
}




