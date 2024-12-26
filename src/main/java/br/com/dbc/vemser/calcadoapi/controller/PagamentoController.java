package br.com.dbc.vemser.calcadoapi.controller;

import br.com.dbc.vemser.calcadoapi.dto.PagamentoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.PagamentoDTO;
import br.com.dbc.vemser.calcadoapi.service.PagamentoService;
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
@RequestMapping("/pagamento")
@Validated
public class PagamentoController {

    private final PagamentoService pagamentoService;

    // LISTAR TODOS OS PAGAMENTOS
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        log.info("Iniciando a listagem de todos os pagamentos.");
        List<PagamentoDTO> pagamentos = pagamentoService.list();
        log.info("Listagem de pagamentos concluída com sucesso. Total de pagamentos encontrados: {}", pagamentos.size());
        return ResponseEntity.ok(pagamentos);
    }

    // CRIAR UM NOVO PAGAMENTO
    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@Valid @RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws Exception {
        log.info("Iniciando a criação de um novo pagamento com os dados: {}", pagamentoCreateDTO);
        PagamentoDTO pagamentoDTO = pagamentoService.create(pagamentoCreateDTO);
        log.info("Pagamento criado com sucesso. ID do novo pagamento: {}", pagamentoDTO.getIdPagamento());
        return ResponseEntity.ok(pagamentoDTO);
    }

    // ATUALIZAR UM PAGAMENTO EXISTENTE
    @PutMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(
            @PathVariable("idPagamento") Integer idPagamento,
            @Valid @RequestBody PagamentoDTO pagamentoDTO) throws Exception {
        log.info("Iniciando a atualização do pagamento com ID: {}. Novos dados: {}", idPagamento, pagamentoDTO);
        PagamentoDTO pagamentoAtualizado = pagamentoService.update(idPagamento, pagamentoDTO);
        log.info("Pagamento com ID: {} atualizado com sucesso.", idPagamento);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    // DELETAR UM PAGAMENTO
    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable("idPagamento") Integer idPagamento) throws Exception {
        log.info("Iniciando a exclusão do pagamento com ID: {}", idPagamento);
        pagamentoService.delete(idPagamento);
        log.info("Pagamento com ID: {} excluído com sucesso.", idPagamento);
        return ResponseEntity.noContent().build();
    }
}
