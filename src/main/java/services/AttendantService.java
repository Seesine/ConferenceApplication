package services;

import model.Attendant;
import model.Conference;
import model.Sections;
import repository.AttendantRepository;

import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AttendantService {
    AttendantRepository attrepo;
    public AttendantService(AttendantRepository attrepo){
        this.attrepo = attrepo;
    }
    /*public List<Conference> getAllConf(){
        return attrepo.allConf();
    }

    public List<Sections> getAllSections(){
        return attrepo.allSections();
    }*/
}
