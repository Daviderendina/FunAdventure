import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.payment.Payment;
import com.rendinadavide.funadventure.service.ServiceFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Access access = TestUtils.createAccess(facade, true);

        Payment retrievedEm = facade.findPaymentById(access.getPayment().getId());
        Payment retrievedDb = em.createQuery("FROM Payment where id = :id", Payment.class)
                .setParameter("id", access.getPayment().getId())
                .getSingleResult();

        assertNotNull(retrievedDb);
        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindAll(){
        Access a1 = TestUtils.createAccess(facade, true);
        Access a2 = TestUtils.createAccess(facade, true);

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

}
