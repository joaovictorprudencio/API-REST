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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class clientes {

   @Autowired
    private ClienteRepository clienteRepository;

@PostMapping("/cliente")
public ResponseEntity <Cliente> SalvarCliente(@RequestBody Cliente cliente){  
Cliente novoCliente = clienteRepository.save(cliente);
return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);

}

@GetMapping("/cliente/{id}")
public ResponseEntity<Cliente> EsseCliente(@PathVariable Long id) {
    Cliente cliente =  clienteRepository.findById(id).orElse(null);
    if (cliente != null) {
        return ResponseEntity.ok(cliente);
    } else {
        return ResponseEntity.notFound().build();
    }
}
   //bug na rota de atulização
   @PutMapping("/cliente/{id}")
   public ResponseEntity<Cliente> AtualizarCliente(@PathVariable Long id, @RequestBody Cliente novoCliente) {
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       if (clienteOptional.isPresent()) {
           Cliente cliente = clienteOptional.get();
           cliente.setNome(novoCliente.getNome());
           cliente.setEmail(novoCliente.getEmail());
           cliente.setCpf(novoCliente.getCpf());
           // Atualizar outros campos conforme necessário
           Cliente clienteAtualizado = clienteRepository.save(cliente);
           return ResponseEntity.ok(clienteAtualizado);
       } else {
           return ResponseEntity.notFound().build();
       }
   
     
}


@DeleteMapping("/cliente/{id}")
public ResponseEntity<Void> DeletarCliente(@PathVariable Long id ){
       clienteRepository.deleteById(id);
       return ResponseEntity.notFound().build();
    }
}




