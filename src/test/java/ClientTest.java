import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.service.ServiceFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class ClientTest {

    ServiceFacade facade = new ServiceFacade();

    private EntityManager em;

    @Before
    public void before(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Test
    public void testCreate(){
        Client client1 = facade.createClient("Client", "1", getFakeDate());
        Client client2 = facade.createClient("Client", "2", getFakeDate());

        List<Client> clientList = em.createQuery("From Client", Client.class).getResultList();

        assertTrue(clientList.contains(client1));
        assertTrue(clientList.contains(client2));
    }

    @Test
    public void testFind(){
        Client client1 = facade.createClient("Client", "1", getFakeDate());

        Client retrievedEm = facade.findClientById(client1.getId());

        Client retrievedDb = (Client) em.createQuery("FROM Client where id = :id")
                .setParameter("id", client1.getId())
                .getSingleResult();

        assertNotNull(retrievedDb);
        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindAll(){
        facade.createClient("Client", "1", getFakeDate());
        facade.createClient("Client", "2", getFakeDate());
        facade.createClient("Client", "3", getFakeDate());

        List<Client> retrievedEm = facade.findAllClient();
        List<Client> retrievedDb = em.createQuery("FROM Client").getResultList();

        retrievedDb.sort(Comparator.comparing(Client::getId));
        retrievedEm.sort(Comparator.comparing(Client::getId));

        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testUpdate(){
        Client c1 = facade.createClient("Client","1", getFakeDate());


        //Update fields
        String newName = "NewClient";
        String newSurname = "New1";
        facade.updateClient(c1, newName, newSurname, getFakeDate());

        Client retrieved = (Client) em.createQuery("From Client where id = :id")
                .setParameter("id", c1.getId())
                .getSingleResult();

        assertEquals(retrieved.getName(), newName);
        assertEquals(retrieved.getSurname(), newSurname);
        assertEquals(retrieved.getbDate(), c1.getbDate());



        //Update companion
        Client companion = facade.createClient("Companion", "1", LocalDate.of(2000,1,1));
        facade.addClientCompanion(c1, companion);

        retrieved = (Client) em.createQuery("From Client where id = :id")
                .setParameter("id", c1.getId())
                .getSingleResult();

        assertEquals(retrieved.getCompanionSet().iterator().next(), companion);
    }

    @Test
    public void testDelete(){
        Client c1 = facade.createClient("","",getFakeDate());
        Client c2 = facade.createClient("","",getFakeDate());

        int cliNumber = em.createQuery("From Client").getResultList().size();

        facade.deleteClient(c1);
        facade.deleteClient(c2);

        int cliNumberDeleted = em.createQuery("From Client").getResultList().size();

        assertEquals(cliNumber, cliNumberDeleted + 2);
    }

    @After
    public void after(){
        em.close();
    }

    private LocalDate getFakeDate(){
        return LocalDate.of(2010,1,1);
    }

}
