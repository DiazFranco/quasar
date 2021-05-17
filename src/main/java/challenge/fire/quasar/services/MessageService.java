package challenge.fire.quasar.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {



    public String getMessage(List<List<String>> messages) {

        String message = generationMessage(messages);
        return message;
    }


    public String generationMessage(List<List<String>> msgs){

        String sentence = "";
        for(List<String> message : msgs){

            if(message.size()>0 && !message.get(0).equals("")){
                sentence = (message.size() == 1) ? message.get(0) : message.get(0) + " ";
                msgs.stream().forEach( x -> x.remove(0));
                return  sentence + generationMessage(msgs);
            }
        }
        return "";
    }
}
