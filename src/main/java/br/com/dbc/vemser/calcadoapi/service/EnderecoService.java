package br.com.dbc.vemser.calcadoapi.service;

import br.com.dbc.vemser.calcadoapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.calcadoapi.dto.EnderecoDTO;
import br.com.dbc.vemser.calcadoapi.entity.Endereco;
import br.com.dbc.vemser.calcadoapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.calcadoapi.repository.ClienteRepository;
import br.com.dbc.vemser.calcadoapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> list() {
        return enderecoRepository.findAll().stream().map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class)).collect(Collectors.toList());
    }

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        Endereco endereco = objectMapper.convertValue(enderecoCreateDTO, Endereco.class);
        if (!clienteRepository.existsById(endereco.getIdCliente())){
            throw new RegraDeNegocioException("O cliente informado com o ID: " + endereco.getIdCliente() + " não existe.");
        }
        return objectMapper.convertValue(enderecoRepository.insert(endereco), EnderecoDTO.class);
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO request) throws Exception {
        Endereco endereco = objectMapper.convertValue(request, Endereco.class);
        if (!clienteRepository.existsById(endereco.getIdCliente())){
            throw new RegraDeNegocioException("O cliente informado com o ID: " + endereco.getIdCliente() + " não existe.");
        }
        return objectMapper.convertValue(enderecoRepository.update(idEndereco, endereco), EnderecoDTO.class);
    }

    public void delete(Integer idEndereco) throws Exception {
        if (!enderecoRepository.existsById(idEndereco)) {
            throw new RegraDeNegocioException("O endereço informado com o ID: " + idEndereco + " não existe.");
        }
        enderecoRepository.deleteById(idEndereco);
    }
}
