package com.inheritchain.messages.repository;

import com.inheritchain.messages.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByAdminAddressAndInheritanceContractAddress(String adminAddress, String inheritanceContractAddress);

    @Query("SELECT m FROM Message m JOIN m.heirAddresses h WHERE h.heirAddress = :heirAddress " +
            "AND m.inheritanceContractAddress = :inheritanceContractAddress")
    List<Message> findAllByHeirAddress(String heirAddress, String inheritanceContractAddress);
}
