package services;

import model.Attendant;
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
}
