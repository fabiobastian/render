package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.ProdutoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ProdutoDTO;
import br.com.dbc.vemser.calcadoapi.entity.Produto;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public ProdutoDTO create(ProdutoCreateDTO produtoCreateDTO) {
        Produto produto = objectMapper.convertValue(produtoCreateDTO, Produto.class);
        return objectMapper.convertValue(produtoRepository.insert(produto), ProdutoDTO.class);
    }

    public List<ProdutoDTO> list() {
        return produtoRepository.findAll().stream().map(produto -> objectMapper.convertValue(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }

    public ProdutoDTO update(Integer idProduto, ProdutoCreateDTO request) throws Exception {
        Produto produto = objectMapper.convertValue(request, Produto.class);
        if (!produtoRepository.existsById(idProduto)) {
            throw new RegraDeNegocioException("Produto com id " + idProduto + " não existe.");
        }
        return objectMapper.convertValue(produtoRepository.update(idProduto, produto), ProdutoDTO.class);
    }

    public void delete(Integer idProduto) throws Exception {
        if (!produtoRepository.existsById(idProduto)) {
            throw new RegraDeNegocioException("Produto com id " + idProduto + " não existe.");
        }
        produtoRepository.deleteById(idProduto);
    }
}