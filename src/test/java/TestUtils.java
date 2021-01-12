import com.rendinadavide.funadventure.domain.Access;
import com.rendinadavide.funadventure.domain.Client;
import com.rendinadavide.funadventure.domain.Equipment;
import com.rendinadavide.funadventure.domain.payment.PaymentType;
import com.rendinadavide.funadventure.service.ServiceFacade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Access createAccess(ServiceFacade facade, boolean closed){

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

        Access access = facade.createAccess(clientList, equipmentList);
        if(closed){
            facade.payAndCloseAccess(access, 50, PaymentType.CASH);
        }
        return access;
    }

    public static Access createAccess(ServiceFacade facade){
        return createAccess(facade, false);
    }

    public static LocalDate getFakeDate(){
        return LocalDate.of(2010,1,1);
    }


}
