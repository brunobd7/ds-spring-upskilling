package com.dantas.springupskilling.service;

import com.dantas.springupskilling.dto.ClientDTO;
import com.dantas.springupskilling.entity.Client;
import com.dantas.springupskilling.exception.DatabaseException;
import com.dantas.springupskilling.exception.ResourceNotFoundException;
import com.dantas.springupskilling.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientRepository.save(clientDTO.toEntity());
        return new ClientDTO(client.getId(),client.getName(), client.getCpf(), client.getIncome(), client.getBirthDate(), client.getChildren());
    }

    @Transactional(readOnly = true)
    public ClientDTO findClientById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found !"));
        return new ClientDTO(client.getId(),client.getName(), client.getCpf(), client.getIncome(), client.getBirthDate(), client.getChildren());
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllClients(Pageable pageable) {
        return clientRepository
                .findAll(pageable)
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getIncome(), client.getBirthDate(), client.getChildren()));
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
       try {
           Client client = clientRepository.getReferenceById(id);
           BeanUtils.copyProperties(clientDTO, client, "id");
           client = clientRepository.save(client);
           return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getIncome(), client.getBirthDate(), client.getChildren());
       }catch (EntityNotFoundException | FatalBeanException exception){
           throw new ResourceNotFoundException("Client not found !");
       }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClient(Long id){
        if(!clientRepository.existsById(id))
            throw new ResourceNotFoundException("Client not found !");

        try{
            clientRepository.deleteById(id);
        }catch(DataIntegrityViolationException exception){
            throw new DatabaseException("Error deleting client !");
        }
    }

}
