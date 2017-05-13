package services;

import model.Sections;
import repository.SectionRepository;

import java.util.List;

/**
 * Created by stefanvacareanu on 13/05/2017.
 */
public class SectionService {
    SectionRepository repo;

    public SectionService(SectionRepository repo){
        this.repo = repo;
    }

    public List<Sections> getAll(){
        return repo.getAll();
    }

    public Integer addSection(int idConf,int sesC, String name){
        return repo.addSection(idConf,sesC,name);
    }

    public void updateSection(Integer SectionID, int sesC, String name ){
        repo.updateSection(SectionID,sesC,name);
    }

    public void deleteSection(Integer SectionID){
        repo.deleteSection(SectionID);
    }
}
