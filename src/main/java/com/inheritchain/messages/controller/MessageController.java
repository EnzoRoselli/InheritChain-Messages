package com.inheritchain.messages.controller;

import com.inheritchain.messages.model.Message;
import com.inheritchain.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody Message message) {
        return ResponseEntity.created(getLocation(messageService.save(message))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Message>> findByAdminAddressAndInheritanceContractAddress(@RequestParam String adminAddress, @RequestParam String inheritanceContractAddress) {
        List<Message> messages = messageService.findByAdminAddressAndInheritanceContractAddress(adminAddress, inheritanceContractAddress);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{messageId}/heir-addresses")
    public ResponseEntity<Message> addHeirAddress(@PathVariable Long messageId, @RequestBody List<String> heirAddresses) {
        return ResponseEntity.ok(messageService.updateHeirAddresses(messageId, heirAddresses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/heir-addresses")
    public ResponseEntity<List<Message>> getMessagesByHeirAddressAndInheritanceContractAddress(@RequestParam String heirAddress, @RequestParam String inheritanceContractAddress) {
        List<Message> messages = messageService.getMessagesByHeirAddress(heirAddress, inheritanceContractAddress);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }

    private URI getLocation(Message message) {
        return URI.create("/messages/" + message.getId());
    }
}
