
import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.domain.payment.CashPayment;
import com.rendinadavide.funadventure.domain.payment.Payment;
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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentTest {

    ServiceFacade facade = new ServiceFacade();

    private EntityManager em;

    @Before
    public void before(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Test
    public void testFind(){
        Access access = createAccess();

        Payment retrievedEm = facade.findPaymentById(access.getPayment().getId());
        Payment retrievedDb = em.createQuery("FROM Payment where id = :id", Payment.class)
                .setParameter("id", access.getPayment().getId())
                .getSingleResult();

        assertNotNull(retrievedDb);
        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindAll(){
        Access a1 = createAccess();
        Access a2 = createAccess();

        List<Payment> retrievedEm = facade.findAllPayment();
        List<Payment> retrievedDb = em.createQuery("FROM Payment", Payment.class).getResultList();

        retrievedDb.sort(Comparator.comparing(Payment::getId));
        retrievedEm.sort(Comparator.comparing(Payment::getId));

        assertEquals(retrievedDb, retrievedEm);
    }

    @After
    public void after(){
        em.close();
    }

    private Access createAccess(){
        Client client1 = facade.createClient("Client", "1", LocalDate.of(2010,1,1));
        Equipment equipment1 = facade.createEquipment(LocalDate.of(2020,1,1), "");
        Client client2 = facade.createClient("Client", "1", LocalDate.of(2010,1,1));
        Equipment equipment2 = facade.createEquipment(LocalDate.of(2020,1,1), "");

        List<Client> clientList = new ArrayList<>();
        clientList.add(client1);
        clientList.add(client2);

        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(equipment1);
        equipmentList.add(equipment2);

        Access access = facade.createAccess(clientList, equipmentList);
        facade.payAndCloseAccess(access, 50, PaymentType.CASH);
        return access;
    }
}
