package br.com.dbc.vemser.calcadoapi.controller;

import br.com.dbc.vemser.calcadoapi.dto.ProdutoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ProdutoDTO;
import br.com.dbc.vemser.calcadoapi.service.ProdutoService;
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
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    private final ProdutoService produtoService;

    // LISTAR TODOS OS PRODUTOS
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        log.info("Iniciando a listagem de todos os produtos.");
        List<ProdutoDTO> produtos = produtoService.list();
        log.info("Listagem de produtos concluída com sucesso. Total de produtos encontrados: {}", produtos.size());
        return ResponseEntity.ok(produtos);
    }

    // CRIAR UM NOVO PRODUTO
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) {
        log.info("Iniciando a criação de um novo produto com os dados: {}", produtoCreateDTO);
        ProdutoDTO produtoDTO = produtoService.create(produtoCreateDTO);
        log.info("Produto criado com sucesso. ID do novo produto: {}", produtoDTO.getIdProduto());
        return ResponseEntity.ok(produtoDTO);
    }

    // ATUALIZAR UM PRODUTO EXISTENTE
    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(
            @PathVariable("idProduto") Integer idProduto,
            @Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) throws Exception {
        log.info("Iniciando a atualização do produto com ID: {}. Novos dados: {}", idProduto, produtoCreateDTO);
        ProdutoDTO produtoAtualizado = produtoService.update(idProduto, produtoCreateDTO);
        log.info("Produto com ID: {} atualizado com sucesso.", idProduto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // DELETAR UM PRODUTO
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("idProduto") Integer idProduto) throws Exception {
        log.info("Iniciando a exclusão do produto com ID: {}", idProduto);
        produtoService.delete(idProduto);
        log.info("Produto com ID: {} excluído com sucesso.", idProduto);
        return ResponseEntity.noContent().build();
    }
}
