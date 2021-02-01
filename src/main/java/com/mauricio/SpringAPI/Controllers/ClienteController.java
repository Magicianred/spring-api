package com.mauricio.SpringAPI.Controllers;

import com.mauricio.SpringAPI.Model.Cliente;
import com.mauricio.SpringAPI.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @GetMapping
    public List<Cliente> listar() {
        return  clienteRepository.findAll();
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable long id)
    {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent())
        {
            clienteRepository.delete(cliente.get());
            return "Cliente " + cliente.get().getNome() + " deletado com sucesso!";
        }
        return "Cliente não encontrado!";
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente, @PathVariable long id) {
        try {
            Cliente clienteExiste = clienteRepository.getOne(id);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
