package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.PagamentoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.PagamentoDTO;
import br.com.dbc.vemser.calcadoapi.entity.Pagamento;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.PagamentoRepository;
import br.com.dbc.vemser.calcadoapi.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ObjectMapper objectMapper;
    private final PedidoRepository pedidoRepository;

    public PagamentoDTO create(PagamentoCreateDTO request) throws Exception {
        Pagamento pagamento = objectMapper.convertValue(request, Pagamento.class);
        if (!pedidoRepository.existsById(pagamento.getIdPedido())) {
            throw new RegraDeNegocioException("Pedido com id " + pagamento.getIdPedido() + " não existe.");
        }
        pagamento.setValor(pedidoRepository.getValorTotalById(pagamento.getIdPedido()));
        return objectMapper.convertValue(pagamentoRepository.insert(pagamento), PagamentoDTO.class);
    }

    public List<PagamentoDTO> list() {
        return pagamentoRepository.findAll().stream().map(pagamento -> objectMapper.convertValue(pagamento, PagamentoDTO.class)).collect(Collectors.toList());
    }

    public PagamentoDTO update(Integer idPagamento, PagamentoDTO request) throws Exception {
        Pagamento pagamento = objectMapper.convertValue(request, Pagamento.class);
        if (!pagamentoRepository.existsById(pagamento.getIdPagamento())) {
            throw new RegraDeNegocioException("Pagamento com id " + pagamento.getIdPagamento() + " não existe.");
        }
        return objectMapper.convertValue(pagamentoRepository.update(idPagamento, pagamento), PagamentoDTO.class);
    }

    public void delete(Integer idPagamento) throws Exception {
        if (!pagamentoRepository.existsById(idPagamento)) {
            throw new RegraDeNegocioException("Pagamento com id " + idPagamento + " não existe.");
        }
        pagamentoRepository.deleteById(idPagamento);
    }
}
