package challenge.fire.quasar.controllers;

import challenge.fire.quasar.domain.SatelliteOperation;
import challenge.fire.quasar.exceptions.MessageException;
import challenge.fire.quasar.services.QuasarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/topsecret")
public class CommunicationController {


    @Autowired
    public CommunicationController(QuasarService quasarService) {
        this.quasarService = quasarService;
    }

    private QuasarService quasarService;


    @PostMapping("/")
    public ResponseEntity topSecret(RequestEntity<SatelliteOperation> requestEntity) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(quasarService.getImperialShip(requestEntity));
        } catch (MessageException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
