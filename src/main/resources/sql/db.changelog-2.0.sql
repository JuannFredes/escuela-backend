--liquibase formatted sql

--changeset juan:1
INSERT IGNORE INTO `rols` VALUES
(1, "ADMIN"),
(2, "USUARIO"),
(3, "INVITADO");
