package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.ClienteCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.ClienteDTO;
import br.com.dbc.vemser.calcadoapi.entity.Cliente;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public List<ClienteDTO> list() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class)).collect(Collectors.toList());
    }

    public ClienteDTO create(ClienteCreateDTO request) {
        Cliente cliente = objectMapper.convertValue(request, Cliente.class);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.insert(cliente), ClienteDTO.class);
        emailService.enviarEmailCriacao(clienteDTO.getEmail(), "Conta criada com sucesso.", clienteDTO.getNome());
        return clienteDTO;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO request) {
        Cliente cliente = objectMapper.convertValue(request, Cliente.class);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRepository.update(idCliente, cliente), ClienteDTO.class);
        emailService.enviarEmailEdicao(clienteDTO.getEmail(), "Conta editada com sucesso.", clienteDTO.getNome());
        return clienteDTO;
    }

    public void delete(Integer idCliente) throws Exception {
        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null){
            throw new RegraDeNegocioException("O cliente informado com o ID: " + idCliente + " não existe.");
        }
        clienteRepository.deleteById(idCliente);
        emailService.enviarEmailExclusao(cliente.getEmail(), "Conta excluida com sucesso.", cliente.getNome());
    }
}
