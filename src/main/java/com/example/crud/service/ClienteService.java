package com.example.crud.service;

import com.example.crud.models.Cliente;
import com.example.crud.repositorio.ClienteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;



    // Construtor que aceita um ClienteRepository como parâmetro
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }



    public Cliente SalvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void DeletarCliente(Long id){
        clienteRepository.deleteById(id);
        }


    public Optional<Cliente> BuscarPorId(Long id){
    Optional<Cliente> cliente = clienteRepository.findById(id);
        return Optional.ofNullable(cliente.orElse(null)); 
    }
    
    public Optional<Cliente> AtualizarCliente(Long id, Cliente clienteAtualizado){
      Optional<Cliente> ClienteOpcional = clienteRepository.findById(id);
      if(ClienteOpcional.isPresent()){
           Cliente novoCliente =  ClienteOpcional.get();
           novoCliente.setNome(clienteAtualizado.getNome());
           novoCliente.setEmail(clienteAtualizado.getEmail());
           novoCliente.setCpf(clienteAtualizado.getCpf());
           return Optional.of(clienteRepository.save(clienteAtualizado));
    } else{
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}

    
}
