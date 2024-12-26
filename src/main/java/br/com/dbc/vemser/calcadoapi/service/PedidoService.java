package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.PedidoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.PedidoDTO;
import br.com.dbc.vemser.calcadoapi.entity.Pedido;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.ClienteRepository;
import br.com.dbc.vemser.calcadoapi.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public PedidoDTO create(PedidoCreateDTO request) throws Exception {
        Pedido pedido = objectMapper.convertValue(request, Pedido.class);
        if (!clienteRepository.existsById(pedido.getIdCliente())) {
            throw new RegraDeNegocioException("O cliente informado com o ID: " + pedido.getIdCliente() + " não existe.");
        }
        pedido.setData(LocalDate.now());
        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoRepository.insert(pedido), PedidoDTO.class);
        pedidoDTO.setValor(0.0);
        return pedidoDTO;
    }

    public List<PedidoDTO> list() {
        return pedidoRepository.findAll().stream().map(pedido -> {
            PedidoDTO dto = objectMapper.convertValue(pedido, PedidoDTO.class);
            dto.setValor(pedidoRepository.getValorTotalById(pedido.getIdPedido()));
            return dto;
        }).collect(Collectors.toList());
    }

    public PedidoDTO update(Integer idPedido, PedidoCreateDTO request) throws Exception {
        Pedido pedido = objectMapper.convertValue(request, Pedido.class);
        Pedido recuperado = pedidoRepository.findById(idPedido);
        if (recuperado == null) {
            throw new RegraDeNegocioException("O cliente informado com o ID: " + idPedido + " não existe.");
        }
        pedido.setData(recuperado.getData());
        return objectMapper.convertValue(pedidoRepository.update(idPedido, pedido), PedidoDTO.class);
    }

    public void delete(Integer idPedido) throws Exception {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new RegraDeNegocioException("Pedido com id " + idPedido + " não existe.");
        }
        pedidoRepository.deleteById(idPedido);
    }
}