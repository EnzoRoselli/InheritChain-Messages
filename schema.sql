CREATE DATABASE `InheritChain`;

CREATE TABLE `InheritChain`.`messages` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `admin_address` VARCHAR(42) NOT NULL,
    `message_text` TEXT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `admin_address_UNIQUE` (`admin_address`)
);

CREATE TABLE `InheritChain`.`heir_addresses` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `message_id` INT NOT NULL,
    `heir_address` VARCHAR(42) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `message_id_heir_address_UNIQUE` (`message_id`, `heir_address`),
    CONSTRAINT `fk_heir_addresses_messages_id` FOREIGN KEY (`message_id`) REFERENCES `messages`(`id`) ON DELETE CASCADE
);