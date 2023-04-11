package com.inheritchain.messages.service;

import com.inheritchain.messages.model.HeirAddress;
import com.inheritchain.messages.model.Message;
import com.inheritchain.messages.model.exceptions.NotFoundException;
import com.inheritchain.messages.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message save(Message message) {
        if (message.getHeirAddresses() != null) {
            message.getHeirAddresses().forEach(heirAddress -> heirAddress.setMessage(message));
        }

        return messageRepository.save(message);
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new NotFoundException("Message with id " + id + " not found"));
    }

    public List<Message> findByAdminAddressAndInheritanceContractAddress(String adminAddress, String inheritanceContractAddress) {
        return messageRepository.findByAdminAddressAndInheritanceContractAddress(adminAddress, inheritanceContractAddress);
    }

    public Message updateHeirAddresses(Long messageId, List<String> heirAddresses) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("Message with id " + messageId + " not found"));

        //Remove heir addresses not present in the list sent by parameters.
        message.getHeirAddresses().removeIf(existingHeirAddress -> !heirAddresses.contains(existingHeirAddress.getHeirAddress()));

        //Add new heir addresses that are not present in the database.
        heirAddresses.forEach(heirAddress -> {
            boolean alreadyExists = message.getHeirAddresses().stream()
                    .anyMatch(existingHeirAddress -> existingHeirAddress.getHeirAddress().equals(heirAddress));

            if (!alreadyExists) {
                message.getHeirAddresses().add(HeirAddress.builder().heirAddress(heirAddress).message(message).build());
            }
        });

        return messageRepository.save(message);
    }

    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    public List<Message> getMessagesByHeirAddress(String heirAddress, String inheritanceContractAddress) {
        return messageRepository.findAllByHeirAddress(heirAddress, inheritanceContractAddress);
    }
}
