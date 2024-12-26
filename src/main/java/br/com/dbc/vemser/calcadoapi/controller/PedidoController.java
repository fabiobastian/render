package br.com.dbc.vemser.calcadoapi.controller;

import br.com.dbc.vemser.calcadoapi.dto.PedidoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.PedidoDTO;
import br.com.dbc.vemser.calcadoapi.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    // LISTAR TODOS OS PEDIDOS
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        log.info("Iniciando a listagem de todos os pedidos.");
        List<PedidoDTO> pedidos = pedidoService.list();
        log.info("Listagem de pedidos concluída com sucesso. Total de pedidos encontrados: {}", pedidos.size());
        return ResponseEntity.ok(pedidos);
    }

    // CRIAR UM NOVO PEDIDO
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@Valid @RequestBody PedidoCreateDTO pedidoCreateDTO) throws Exception {
        log.info("Iniciando a criação de um novo pedido com os dados: {}", pedidoCreateDTO);
        PedidoDTO pedidoDTO = pedidoService.create(pedidoCreateDTO);
        log.info("Pedido criado com sucesso. ID do novo pedido: {}", pedidoDTO.getIdPedido());
        return ResponseEntity.ok(pedidoDTO);
    }

    // ATUALIZAR UM PEDIDO EXISTENTE
    @PutMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> atualizarPedido(
            @PathVariable("idPedido") Integer idPedido,
            @Valid @RequestBody PedidoCreateDTO pedidoCreateDTO) throws Exception {
        log.info("Iniciando a atualização do pedido com ID: {}. Novos dados: {}", idPedido, pedidoCreateDTO);
        PedidoDTO pedidoAtualizado = pedidoService.update(idPedido, pedidoCreateDTO);
        log.info("Pedido com ID: {} atualizado com sucesso.", idPedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    // DELETAR UM PEDIDO
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> deletarPedido(@PathVariable("idPedido") Integer idPedido) throws Exception {
        log.info("Iniciando a exclusão do pedido com ID: {}", idPedido);
        pedidoService.delete(idPedido);
        log.info("Pedido com ID: {} excluído com sucesso.", idPedido);
        return ResponseEntity.noContent().build();
    }
}
