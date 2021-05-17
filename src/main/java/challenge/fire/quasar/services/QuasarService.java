package challenge.fire.quasar.services;

import challenge.fire.quasar.domain.ImperialShip;
import challenge.fire.quasar.exceptions.MessageException;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

public interface QuasarService {

     ImperialShip getImperialShip(RequestEntity requestEntity) throws MessageException;
}
