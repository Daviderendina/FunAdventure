# FunAdventure

Link alla repository: https://gitlab.com/daviderendina/2020_assignment3_meet_your_friends

Il progetto è stato svolto in autonomia dallo studente Rendina Davide matricola 830730.

## Descrizione dell'applicazione
FunAdventure è un applicativo che permette la gestione degli ingressi in un parco avventura. Attraverso questa applicazione, è possibile effettuare la registrazione dei clienti; per i clienti minorenni, è necessario specificare quale degli altri clienti è il suo accompagnatore (che deve essere maggiorenne). Uno o più clienti effettuano un INGRESSO nel parco, viene fornita loro un ATTREZZATURA (tipicamente, un casco) e cominciano ad effettuare i percorsi desiderati all'interno del parco stesso. Al termine della permanenza di tutti i clienti associati a un particolare ingresso, è possibile effettuare il PAGAMENTO dei percorsi effettuati mediante tre tipologie: VOUCHER, CARTA e CONTANTI.

## Diagramma di dominio

![](img/dominio.PNG)

### Descrizione delle classi

Ogni classe ha un attributo di tipo integer chiamato id, che rappresenta il UID dato dal sistema all'oggetto.

#### Client
Rappresenta un cliente registrato nel sistema del parco avventura, descritto dai suoi dati anagrafici (nome, cognome, data di nascita). Un *Client* può essere in relazione con altri n *Client* (*accompanied_by*); questa relazione indica che il cliente dalla parte n della relazione è il maggiorenne accompagnatore (*companion*) dell'altro.

#### Relazione: park_access
Mette in relazione un accesso al parco con i *Client* che l'hanno effettuato. In particolare la relazione è di cardinalità n:n poichè ad un unico accesso al parco possono essere collegati più clienti (ad esempio per i gruppi) mentre un'unico cliente può essere presente in diversi ingressi (ad esempio, in giornate diverse).

#### Entrance
Questa entità rappresenta un singolo ingresso che viene effettuato al parco. Un *Entrance* viene definita solamente dai due timestamp di creazione dell'ingresso (entrata dei clienti nel parco) e di chiusura e pagamento dello stesso (uscita di tutti i clienti dal parco).
Un *Entrance* è in relazione con un pagamento (con cardinalità 1 - 0..1) che rappresenta il pagamento effettuato per lo specifico ingresso; il pagamento è definito con relazione 0 poichè avviene in un momento successivo alla creazione dell'ingresso. 
<!-- Un ingresso inoltre contiene due insiemi rispettivamente di clienti (che rappresenta appunto il cliente singolo/gruppo che accede al parco) e di attrezzatura, rappresentante tutte le tipologie di attrezzatura (caschi, imbraghi) utilizzati dai clienti di quella *Entrance*. -->

#### Equipment
Rappresenta un singolo equipaggiamento (casco, imbrago, ..) in possesso del parco avventura. È descritto dalla sua data di acquisto e
dal numero seriale.

#### Payment
Descrive il pagamento effettuato dal/dai clienti nei confronti del parco al termine della loro visita. È descritto da un campo float *amount*, che rappresenta il conto saldato, e dalla data e ora del pagamento. *Payment* è una generalizzazione di tre diverse entità: *Voucher*, *CreditCard* e *Cash*.

#### Voucher
Rappresenta il pagamento effettuato tramite voucher dal cliente. Un pagamento di questo tipo è descritto anche dal numero seriale del voucher utilizzato dal cliente.

#### CreditCard (da chiamare solo card)
Rappresenta il pagamento effettuato tramite carta/bancomat, e aggiunge un nuovo campo che contiene il numero di transazione del pagamento.

#### Cash
Rappresenta il pagamento effettuato tramite contanti.


## Database: Schema logico
![](img/modelloLogico.png)
