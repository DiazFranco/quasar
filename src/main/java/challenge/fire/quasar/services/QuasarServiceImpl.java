package challenge.fire.quasar.services;

import challenge.fire.quasar.domain.Hauler;
import challenge.fire.quasar.domain.ImperialShip;
import challenge.fire.quasar.domain.Position;
import challenge.fire.quasar.domain.SatelliteOperation;
import challenge.fire.quasar.exceptions.MessageException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Configurable
public class QuasarServiceImpl implements QuasarService{


    @Autowired
    public QuasarServiceImpl(MessageService messageService, LocationService locationService, Environment environment) {
        this.messageService = messageService;
        this.locationService = locationService;
        this.environment = environment;
    }

    private MessageService messageService;

    private LocationService locationService;

    private Environment environment;


    public ImperialShip getImperialShip(RequestEntity requestEntity) throws MessageException {

        SatelliteOperation satelliteOperation = (SatelliteOperation) requestEntity.getBody();

        if(satelliteOperation.getMessages().size() < 2)
            throw new MessageException("Nùmero de mensajes insuficientes");

        String message = messageService.getMessage(satelliteOperation.getMessages());

        String[] satellitePosition;

        if (satelliteOperation.getPositions()[0] == null) {
            int satellites = Integer.parseInt(environment.getProperty("numbers.satellites"));
            double [][] points = new double[satellites][];
            for (int i = 0; i < satelliteOperation.getSatellites().size(); i++) {

                satellitePosition = environment.getProperty("satellites." + i + ".position").split(",");
                points[i] = Arrays.stream(satellitePosition).map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            satelliteOperation.setPositions(points);
        }

        double [] points = locationService.getLocation(satelliteOperation.getPositions(), satelliteOperation.getDistances());
        Position position = new Position(points);

        return new Hauler(position, message);

    }
}
