package challenge.fire.quasar.services;

import challenge.fire.quasar.domain.*;
import challenge.fire.quasar.exceptions.MessageException;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

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


    public ImperialShip getImperialShip(SatelliteOperation satelliteOperation) throws MessageException {

        if(satelliteOperation.getMessages().size() < 2)
            throw new MessageException("Nùmero de mensajes insuficientes");

        String message = messageService.getMessage(satelliteOperation.getMessages());

        String[] satellitePosition;

        if (satelliteOperation.getPositions()[0] == null) {
            int satellites = Integer.parseInt(environment.getProperty("numbers.satellites"));
            double [][] points = new double[satellites][];
            try {
            for (int i = 0; i < satelliteOperation.getSatellites().size(); i++) {

                satellitePosition = environment.getProperty("satellites." + i + ".position").split(",");
                points[i] = Arrays.stream(satellitePosition).map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            satelliteOperation.setPositions(points);

            } catch (NullPointerException e){
                throw new MessageException("No hay datos suficientes");
            }
        }

        double [] points = locationService.getLocation(satelliteOperation.getPositions(), satelliteOperation.getDistances());
        Position position = new Position(points);

        return new Hauler(position, message);

    }

    public JSONObject getJsonFromMap(Map<String, Satellite> map) throws JSONException {
        JSONObject jsonData = new JSONObject();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map<?, ?>) {
                value = getJsonFromMap((Map<String, Satellite>) value);
            }
            jsonData.put(key, value);
        }
        return jsonData;
    }
}
