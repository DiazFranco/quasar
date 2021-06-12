package challenge.fire.quasar.services;

import challenge.fire.quasar.domain.ImperialShip;
import challenge.fire.quasar.domain.Satellite;
import challenge.fire.quasar.domain.SatelliteOperation;
import challenge.fire.quasar.exceptions.MessageException;
import net.minidev.json.JSONObject;
import org.json.JSONException;

import java.util.Map;

public interface QuasarService {

     ImperialShip getImperialShip(SatelliteOperation satelliteOperation) throws MessageException;

     JSONObject getJsonFromMap(Map<String, Satellite> map) throws JSONException;
}
