package com.example.crud.Serviços;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.crud.models.Cliente;
import com.example.crud.repositorio.ClienteRepository;
import com.example.crud.service.ClienteService;

public class ClienteServiceTest {
    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;


     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService(clienteRepository);
    }

   @Test
   void testSalvarCliente(){
    //simulação de datos local
    Cliente cliente = new Cliente((long) 1,"joao");
//mockando o comportamneto do repositorio, faznedo ele retornar o objeto
    when(clienteRepository.save(cliente)).thenReturn(cliente);
    //criando o metodo a ser testado localmente 
    Cliente clienteSalvo = clienteService.SalvarCliente(cliente);
    assertNotNull(clienteSalvo);
    assertEquals("joao", clienteSalvo.getNome());
    
   }

     @Test
     void testBuscarCliente(){
       //criando objeto local
    Cliente cliente = new Cliente((long) 2,"joao");
      //simulando jpa
    when(clienteRepository.findById((long) 2)).thenReturn(java.util.Optional.of(cliente));
     //passando o t6ipo de Optional Para cliente
    Optional<Cliente> clienteOptional = clienteService.BuscarPorId((long) 2);
      // fazendo testes e comparações

           
        Cliente cliente2 = clienteOptional.get();
       // fazendo testes e comparações
    assertNotNull(cliente2);
   assertEquals("joao", cliente2.getNome());
}

@Test
void TestAtualizarCliente(){
    Cliente cliente = new Cliente((long) 1, "joao");
   Cliente clienteAtualizado = new Cliente((long) 1, "joãovictor");

  when(clienteRepository.findById((long) 1)).thenReturn(Optional.of(cliente));
  when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);


  Optional<Cliente> clienteOptional = clienteService.AtualizarCliente((long) 1 ,  clienteAtualizado );

  Cliente clienteAtual = clienteOptional.get();
  
  assertEquals("joãovictor", clienteAtual.getNome());


  
}



}
