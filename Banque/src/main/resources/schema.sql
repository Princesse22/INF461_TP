CREATE TABLE utilisateur (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    telephone VARCHAR(20),
    type VARCHAR(30)
);

CREATE TABLE operateur (
    id_operateur BIGINT PRIMARY KEY AUTO_INCREMENT,
    solde DECIMAL(15,2),
    statut VARCHAR(30)
);

CREATE TABLE compte (
    id_compte BIGINT PRIMARY KEY AUTO_INCREMENT,
    solde DECIMAL(15,2),
    statut VARCHAR(30),
    id_operateur BIGINT,
    FOREIGN KEY (id_operateur) REFERENCES operateur(id_operateur)
);

-- Transaction
CREATE TABLE transaction (
    id_transaction BIGINT PRIMARY KEY AUTO_INCREMENT,
    montant DECIMAL(15,2),
    id_compte BIGINT,
    FOREIGN KEY (id_compte) REFERENCES compte(id_compte)
);

-- Authentification
CREATE TABLE authentification (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(30),
    id_transaction BIGINT,
    FOREIGN KEY (id_transaction) REFERENCES transaction(id_transaction)
);

-- Notification
CREATE TABLE notification (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(255),
    priorite VARCHAR(20)
);

-- Fournisseur SMS
CREATE TABLE fournisseur_sms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100)
);

-- Workflow Transaction
CREATE TABLE workflow_transaction (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(30)
);

-- Compte Decorator
CREATE TABLE compte_decorator (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(30),
    id_compte BIGINT,
    FOREIGN KEY (id_compte) REFERENCES compte(id_compte)
);