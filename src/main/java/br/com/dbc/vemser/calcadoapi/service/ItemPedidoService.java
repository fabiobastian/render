package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.ItemPedidoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ItemPedidoDTO;
import br.com.dbc.vemser.calcadoapi.entity.ItemPedido;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.ItemPedidoRepository;
import br.com.dbc.vemser.calcadoapi.repository.PedidoRepository;
import br.com.dbc.vemser.calcadoapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ObjectMapper objectMapper;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public List<ItemPedidoDTO> list() {
        List<ItemPedido> itensPedido = itemPedidoRepository.findAll();
        return itensPedido.stream().map(itemPedido -> objectMapper.convertValue(itemPedido, ItemPedidoDTO.class)).collect(Collectors.toList());
    }

    public ItemPedidoDTO create(ItemPedidoCreateDTO request) throws Exception {
        ItemPedido itemPedido = objectMapper.convertValue(request, ItemPedido.class);
        if (!pedidoRepository.existsById(itemPedido.getIdPedido())) {
            throw new RegraDeNegocioException("Pedido com id " + itemPedido.getIdPedido() + " não existe.");
        } else if (!produtoRepository.existsById(itemPedido.getIdProduto())) {
            throw new RegraDeNegocioException("Produto com id " + itemPedido.getIdProduto() + " não existe.");
        }
        return objectMapper.convertValue(itemPedidoRepository.insert(itemPedido), ItemPedidoDTO.class);
    }

    public ItemPedidoDTO update(Integer idItemPedido, ItemPedidoCreateDTO request) throws Exception {
        ItemPedido itemPedido = objectMapper.convertValue(request, ItemPedido.class);
        if (!itemPedidoRepository.existsById(idItemPedido)) {
            throw new RegraDeNegocioException("ItemPedido com id " + idItemPedido + " não existe.");
        } else if (!pedidoRepository.existsById(itemPedido.getIdPedido())) {
            throw new RegraDeNegocioException("Pedido com id " + itemPedido.getIdPedido() + " não existe.");
        } else if (!produtoRepository.existsById(itemPedido.getIdProduto())) {
            throw new RegraDeNegocioException("Produto com id " + itemPedido.getIdProduto() + " não existe.");
        }
        return objectMapper.convertValue(itemPedidoRepository.update(idItemPedido, itemPedido), ItemPedidoDTO.class);
    }

    public void delete(Integer idItemPedido) throws Exception {
        if (!itemPedidoRepository.existsById(idItemPedido)) {
            throw new RegraDeNegocioException("ItemPedido com id " + idItemPedido + " não existe.");
        }
        itemPedidoRepository.deleteById(idItemPedido);
    }
}
