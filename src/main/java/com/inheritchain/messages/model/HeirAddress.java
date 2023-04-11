package com.inheritchain.messages.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "heir_addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class HeirAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false, foreignKey = @ForeignKey(name = "fk_heir_addresses_messages_id", foreignKeyDefinition = "ON DELETE CASCADE"))
    private Message message;

    @Column(name = "heir_address", nullable = false, length = 42)
    private String heirAddress;
}
