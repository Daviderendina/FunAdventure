# FunAdventure

## Studenti
Il progetto è stato svolto in autonomia dallo studente Rendina Davide matricola 830730.

## Descrizione del dominio
FunAdventure è un applicativo che permette la gestione degli ingressi in un parco avventura. Attraverso questa applicazione, è possibile effettuare la registrazione dei clienti; per i clienti minorenni, è necessario specificare quale degli altri clienti è il suo accompagnatore (che deve essere maggiorenne). Ogni cliente effettua un INGRESSO nel parco, gli viene fornita un ATTREZZATURA e comincia ad effettuare i percorsi desiderati. Al termine della sua permanenza, è possibile effettuare il PAGAMENTO mediante tre tipologie differenti: voucher, carta, contanti.

## Descrizione delle entità persistenti

< immagine schema ER >

#### Client
Rappresenta un cliente registrato nel sistema del parco avventura, descritto dai suoi dati anagrafici (nome, cognome, data di nascita) e da un id progressivo, che rappresenta la primary key della tabella. Un cliente (che indicheremo con cliente1) può essere in relazione *companion* con altri n clienti (cliente2), e una relazione di questo tipo indica che il cliente1 è accompagnatore del cliente 2.

#### Relazione: park_access
Mette in relazione l'accesso al parco con i clienti che l'hanno effettuato. In particolare la relazione è di cardinalità n:n poichè ad un unico accesso al parco possono essere collegati più clienti (ad esempio per i gruppi) mentre un'unico cliente può essere presente in diversi ingressi (ad esempio, in giornate diverse).

#### Entrance
#### Equipment
#### Payment
#### Voucher
#### CreditCard
#### Cash