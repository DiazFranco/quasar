package challenge.fire.quasar.controllers;

import challenge.fire.quasar.domain.Satellite;
import challenge.fire.quasar.domain.SatelliteOperation;
import challenge.fire.quasar.exceptions.MessageException;
import challenge.fire.quasar.repository.SatelliteRepository;
import challenge.fire.quasar.services.QuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topsecret")
public class CommunicationController {

    @Autowired
    public CommunicationController(QuasarService quasarService, SatelliteRepository satelliteRepository) {
        this.quasarService = quasarService;
        this.satelliteRepository = satelliteRepository;
    }


    private QuasarService quasarService;

    private SatelliteRepository satelliteRepository;

    private int count;



    @PostMapping("/")
    public ResponseEntity topSecret(RequestEntity<SatelliteOperation> requestEntity) {

        try {
            SatelliteOperation satelliteOperation = requestEntity.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(quasarService.getImperialShip(satelliteOperation));
        } catch (MessageException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity topSecretSplit(@RequestBody Satellite satellite, @PathVariable String satellite_name){
        try {
            Satellite sat = satelliteRepository.findByName(satellite_name);
            if(sat != null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un satelite registrado con ese nombre");
            }

            Map<String,Satellite> satellites = satelliteRepository.findAll();
           if (!satellites.isEmpty()){
               if(satellites.size() == 3){
                   return ResponseEntity.status(HttpStatus.CONFLICT).body("No pueden haber más de 3 satelites registrados");
               }
           }
                satellite.setName(satellite_name);
                satelliteRepository.save(satellite);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/topsecret_split")
    public Map<String,Satellite> findAll(){
        return satelliteRepository.findAll();
    }

    @GetMapping("/topsecret_split/getPosition")
    public ResponseEntity getPosition(){
        try {
           Map<String,Satellite> satellites = satelliteRepository.findAll();
           //TODO Ver lógica
           SatelliteOperation satelliteOperation = new SatelliteOperation();
           return ResponseEntity.status(HttpStatus.OK).body(quasarService.getImperialShip(satelliteOperation));
        }catch (Exception message){
        message.printStackTrace();
        }
        return ResponseEntity.status(200).body(null);
    }


    @DeleteMapping("/topsecret_split/{name}")
    public void deleteSatellite(@PathVariable String name){
         satelliteRepository.delete(name);
    }
}
