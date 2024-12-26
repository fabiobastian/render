package br.com.dbc.vemser.calcadoapi.controller;

import br.com.dbc.vemser.calcadoapi.dto.ClienteCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ClienteDTO;
import br.com.dbc.vemser.calcadoapi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    // LISTAR TODOS OS CLIENTES
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        log.info("Iniciando a listagem de todos os clientes.");
        List<ClienteDTO> clientes = clienteService.list();
        log.info("Listagem de clientes concluída com sucesso. Total de clientes encontrados: {}", clientes.size());
        return ResponseEntity.ok(clientes);
    }

    // CRIAR CLIENTE
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        log.info("Iniciando a criação de um novo cliente com os dados: {}", clienteCreateDTO);
        ClienteDTO clienteDTO = clienteService.create(clienteCreateDTO);
        log.info("Cliente criado com sucesso. ID do novo cliente: {}", clienteDTO.getIdCliente());
        return ResponseEntity.ok(clienteDTO);
    }

    // ATUALIZAR CLIENTE EXISTENTE
    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @NotNull @PathVariable("idCliente") Integer idCliente,
            @Valid @RequestBody ClienteCreateDTO clienteCreateDTO) throws Exception {
        log.info("Iniciando a atualização do cliente com ID: {}. Novos dados: {}", idCliente, clienteCreateDTO);
        ClienteDTO clienteAtualizado = clienteService.update(idCliente, clienteCreateDTO);
        log.info("Cliente com ID: {} atualizado com sucesso.", idCliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // DELETAR CLIENTE
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@NotNull @PathVariable("idCliente") Integer idCliente) throws Exception {
        log.info("Iniciando a exclusão do cliente com ID: {}", idCliente);
        clienteService.delete(idCliente);
        log.info("Cliente com ID: {} excluído com sucesso.", idCliente);
        return ResponseEntity.noContent().build();
    }
}
