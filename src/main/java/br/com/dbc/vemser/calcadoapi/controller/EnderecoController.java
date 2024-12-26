package br.com.dbc.vemser.calcadoapi.controller;


import br.com.dbc.vemser.calcadoapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.EnderecoDTO;
import br.com.dbc.vemser.calcadoapi.service.EnderecoService;
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
@RequestMapping("/endereco")
@Validated
public class EnderecoController {

    private final EnderecoService enderecoService;

    // LISTAR TODOS OS ENDERECOS
    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        log.info("Iniciando a listagem de todos os endereços.");
        List<EnderecoDTO> enderecos = enderecoService.list();
        log.info("Listagem de endereços concluída com sucesso. Total de endereços encontrados: {}", enderecos.size());
        return ResponseEntity.ok(enderecos);
    }

    // CRIAR ENDERECO
    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.info("Iniciando a criação de um novo endereço com os dados: {}", enderecoCreateDTO);
        EnderecoDTO enderecoDTO = enderecoService.create(enderecoCreateDTO);
        log.info("Endereço criado com sucesso. ID do novo endereço: {}", enderecoDTO.getIdEndereco());
        return ResponseEntity.ok(enderecoDTO);
    }

    // ATUALIZAR ENDERECO EXISTENTE
    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(
            @PathVariable("idEndereco") Integer idEndereco,
            @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        log.info("Iniciando a atualização do endereço com ID: {}. Novos dados: {}", idEndereco, enderecoCreateDTO);
        EnderecoDTO enderecoAtualizado = enderecoService.update(idEndereco, enderecoCreateDTO);
        log.info("Endereço com ID: {} atualizado com sucesso.", idEndereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    // DELETAR ENDERECO
    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable("idEndereco") Integer idEndereco) throws Exception {
        log.info("Iniciando a exclusão do endereço com ID: {}", idEndereco);
        enderecoService.delete(idEndereco);
        log.info("Endereço com ID: {} excluído com sucesso.", idEndereco);
        return ResponseEntity.noContent().build();
    }
}
