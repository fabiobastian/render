package br.com.dbc.vemser.calcadoapi.controller;


import br.com.dbc.vemser.calcadoapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.EnderecoDTO;
import br.com.dbc.vemser.calcadoapi.dto.ItemPedidoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ItemPedidoDTO;
import br.com.dbc.vemser.calcadoapi.entity.ItemPedido;
import br.com.dbc.vemser.calcadoapi.service.ItemPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.FetchProfile;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/item-pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    //LISTAR TODOS OS ITENS DE PEDIDO
    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> list() {
        log.info("Iniciando a listagem de todos os itens de pedido.");
        List<ItemPedidoDTO> itensPedido = itemPedidoService.list();
        log.info("Listagem de itens de pedido concluída com sucesso. Total de itens de pedido encontrados: {}", itensPedido.size());
        return ResponseEntity.ok(itensPedido);
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> create(@Valid @RequestBody ItemPedidoCreateDTO itemPedidoCreateDTO) throws Exception {
        log.info("Criando entidade item de pedido.");
        ItemPedidoDTO itemPedidoDTO = itemPedidoService.create(itemPedidoCreateDTO);
        return ResponseEntity.ok(itemPedidoDTO);
    }

    @PutMapping("/{idItemPedido}")
    public ResponseEntity<ItemPedidoDTO> update(@PathVariable("idItemPedido") Integer idItemPedido, @Valid @RequestBody ItemPedidoCreateDTO itemPedidoCreateDTO) throws Exception {
        log.info("Editando entidade item de pedido.");
        ItemPedidoDTO itemPedidoDTO = itemPedidoService.update(idItemPedido, itemPedidoCreateDTO);
        return ResponseEntity.ok(itemPedidoDTO);
    }

    @DeleteMapping("/{idItemPedido}")
    public ResponseEntity<Void> delete(@PathVariable("idItemPedido") Integer idItemPedido) throws Exception {
        log.info("Deletando entidade item de pedido.");
        itemPedidoService.delete(idItemPedido);
        return ResponseEntity.ok().build();
    }
}
