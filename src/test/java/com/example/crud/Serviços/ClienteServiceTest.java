package com.example.crud.Serviços;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
     Optional<Cliente> cliente = new Cliente(2L, "victor");
    Long id = Long.valueOf(2);
     assertNotNull(id);
     when(clienteRepository.findById(2l)).thenReturn(cliente);






     }
  
























}
