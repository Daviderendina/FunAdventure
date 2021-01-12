import com.rendinadavide.funadventure.domain.Equipment;
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

public class EquipmentTest {

    ServiceFacade facade = new ServiceFacade();

    private EntityManager em;

    @Before
    public void before(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FunAdventure");
        em = emf.createEntityManager();
    }

    @Test
    public void testCreate(){
        Equipment equipment1 = facade.createEquipment(TestUtils.getFakeDate(), "");
        Equipment equipment2 = facade.createEquipment(TestUtils.getFakeDate(), "");

        List<Equipment> equipmentList = em.createQuery("From Equipment").getResultList();

        assertTrue(equipmentList.contains(equipment1));
        assertTrue(equipmentList.contains(equipment2));
    }

    @Test
    public void testFind(){
        Equipment equipment1 = facade.createEquipment(TestUtils.getFakeDate(), "");

        Equipment retrievedEm = facade.findEquipmentById(equipment1.getId());
        Equipment retrievedDb = em.createQuery("FROM Equipment where id = :id", Equipment.class)
                .setParameter("id", equipment1.getId())
                .getSingleResult();

        assertNotNull(retrievedDb);
        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testFindAll(){
        facade.createEquipment(TestUtils.getFakeDate(), "");
        facade.createEquipment(TestUtils.getFakeDate(), "");
        facade.createEquipment(TestUtils.getFakeDate(), "");

        List<Equipment> retrievedEm = facade.findAllEquipment();
        List<Equipment> retrievedDb = em.createQuery("FROM Equipment", Equipment.class).getResultList();

        retrievedDb.sort(Comparator.comparing(Equipment::getId));
        retrievedEm.sort(Comparator.comparing(Equipment::getId));

        assertEquals(retrievedDb, retrievedEm);
    }

    @Test
    public void testDelete(){
        Equipment eq1 = facade.createEquipment(TestUtils.getFakeDate(), "");
        Equipment eq2 = facade.createEquipment(TestUtils.getFakeDate(), "");

        int eqNumber = em.createQuery("From Equipment").getResultList().size();

        facade.deleteEquipment(eq1);
        facade.deleteEquipment(eq2);

        int eqNumberDeleted = em.createQuery("From Equipment").getResultList().size();

        assertEquals(eqNumber, eqNumberDeleted + 2);
    }

    @Test
    public void testUpdate(){
        Equipment e1 = facade.createEquipment(TestUtils.getFakeDate(), "");

        String newSn = "NewSn";
        facade.updateEquipment(e1, e1.getPurchaseDate(), newSn);

        Equipment retrieved = em.createQuery("FROM Equipment where id = :id", Equipment.class)
                .setParameter("id", e1.getId())
                .getSingleResult();

        assertEquals(retrieved.getSerialNumber(), newSn);
        assertEquals(retrieved.getPurchaseDate(), e1.getPurchaseDate());
    }

    @After
    public void after(){
        em.close();
    }

}
