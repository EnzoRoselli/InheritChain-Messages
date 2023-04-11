package com.inheritchain.messages.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "messages", schema = "InheritChain")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_address", nullable = false, length = 42)
    private String adminAddress;

    @Column(name = "inheritance_contract_address", nullable = false, length = 42)
    private String inheritanceContractAddress;

    @Column(name = "message_text", nullable = false, columnDefinition = "TEXT")
    private String messageText;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HeirAddress> heirAddresses;
}
