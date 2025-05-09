package com.dantas.springupskilling.resource;

import com.dantas.springupskilling.dto.ClientDTO;
import com.dantas.springupskilling.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClients(Pageable pageable){
        return ResponseEntity.ok(clientService.findAllClients(pageable));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientDTO clientDTO){
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedClient.id())
                .toUri();
        return ResponseEntity.created(uri).body(savedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(id, clientDTO));
    }
}
