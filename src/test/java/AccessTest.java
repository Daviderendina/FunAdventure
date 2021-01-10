import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.domain.payment.CashPayment;
import com.rendinadavide.funadventure.domain.payment.PaymentType;
import com.rendinadavide.funadventure.service.ServiceFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class AccessTest {

    ServiceFacade facade = new ServiceFacade();

    private EntityManager em;

    @Before
    public void before(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Test
    public void testCreate(){
        Access a1 = facade.createAccess(new ArrayList<>(),new ArrayList<>());
        Access a2 = createAccess();

        List<Access> accessList = em.createQuery("From Access", Access.class).getResultList();

        assertTrue(accessList.contains(a1));
        assertTrue(accessList.contains(a2));
    }

    @Test
    public void testFind(){
        Access a1 = createAccess();

        Access retrievedEm = facade.findAccessById(a1.getId());
        Access retrievedDb = em.createQuery("FROM Access where id = :id", Access.class)
                .setParameter("id", a1.getId())
                .getSingleResult();

        assertNotNull(retrievedDb);
        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindAll(){
        createAccess();
        createAccess();

        List<Access> retrievedEm = facade.findAllAccess();
        List<Access> retrievedDb = em.createQuery("FROM Access", Access.class).getResultList();

        retrievedDb.sort(Comparator.comparing(Access::getId));
        retrievedEm.sort(Comparator.comparing(Access::getId));

        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindActive(){
        List<Client> activeClients = facade.findClientWithActiveAccess();
        List<Equipment> activeEquipment = facade.findEquipmentInUse();

        Access a1 = createAccess();
        Access a2 = createAccess();

        facade.payAndCloseAccess(a1, 50, PaymentType.CASH);

        List<Client> newActiveClients = facade.findClientWithActiveAccess();
        List<Equipment> newActiveEquipment = facade.findEquipmentInUse();

        activeClients.addAll(a2.getClientCollection());
        activeEquipment.addAll(a2.getEquipmentCollection());

        activeClients.sort(Comparator.comparing(Client::getId));
        newActiveClients.sort(Comparator.comparing(Client::getId));
        activeEquipment.sort(Comparator.comparing(Equipment::getId));
        newActiveEquipment.sort(Comparator.comparing(Equipment::getId));

        assertEquals(activeClients, newActiveClients);
        assertEquals(activeEquipment, newActiveEquipment);
    }

    @Test
    public void testSearchClientFreeAccess(){
        List<Client> clientFreeAccessStart = facade.findClientWithFreeAccess();

        Access a1 = createAccess();
        Access a2 = createAccess();

        facade.payAndCloseAccess(a1, 50, "", PaymentType.CASH);
        facade.payAndCloseAccess(a2, 0, "", PaymentType.CASH);

        List<Client> clientFreeAccess = facade.findClientWithFreeAccess();
        List<Client> clientFreeAccessQuery = em.createQuery("Select c from Access a join a.clientCollection c join a.payment p where p.amount = 0").getResultList();

        assertEquals(clientFreeAccessStart.size() + 2, clientFreeAccess.size());
        assertEquals(clientFreeAccess, clientFreeAccessQuery);
        assertTrue(clientFreeAccess.containsAll(a2.getClientCollection()));
    }

    @Test
    public void testUpdate(){
        Access access = createAccess();

        int clientNum = access.getClientCollection().size();
        int equipNum = access.getEquipmentCollection().size();

        Client c1 = facade.createClient("Client","1", getFakeDate());
        Equipment e1 = facade.createEquipment(getFakeDate(), "");

        facade.addAccessClient(access, c1);
        facade.addAccessEquipment(access, e1);
        facade.payAndCloseAccess(access, 50, PaymentType.CASH);


        Access retrieved = em.createQuery("From Access where id = :id", Access.class)
                .setParameter("id", access.getId())
                .getSingleResult();

        assertEquals(clientNum + 1, retrieved.getClientCollection().size());
        assertEquals(equipNum + 1, retrieved.getEquipmentCollection().size());
        assertNotNull(retrieved.getExitDate());
        assertNotNull(retrieved.getPayment());

    }

    @Test
    public void testDelete(){
        Access a1 = createAccess();

        int accNumber = em.createQuery("From Access").getResultList().size();

        facade.deleteAccess(a1);
        facade.payAndCloseAccess(a1, 50, "", PaymentType.CASH);

        int accNumberDeleted = em.createQuery("From Access").getResultList().size();

        assertEquals(accNumber, accNumberDeleted + 1);
    }

    @After
    public void after(){
        em.close();
    }

    private LocalDate getFakeDate(){
        return LocalDate.of(2010,1,1);
    }

    private Access createAccess(){
        Client client1 = facade.createClient("Client", "1", getFakeDate());
        Equipment equipment1 = facade.createEquipment(LocalDate.of(2020,1,1), "");
        Client client2 = facade.createClient("Client", "1", getFakeDate());
        Equipment equipment2 = facade.createEquipment(LocalDate.of(2020,1,1), "");

        List<Client> clientList = new ArrayList<>();
        clientList.add(client1);
        clientList.add(client2);

        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(equipment1);
        equipmentList.add(equipment2);

        return facade.createAccess(clientList, equipmentList);
    }

}
