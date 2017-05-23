package services;

import model.Conference;
import model.File;
import model.Sections;
import repository.AuthorsRepository;

import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AuthorService
{
    AuthorsRepository repo;
    public AuthorService(AuthorsRepository repo)
    {
         this.repo = repo;
    }
    public List<File> getAllFiles()
    {
        return repo.getAllFiles();
    }

    public List<Conference> getAllConf()
    {
        return repo.getAllConferences();
    }

    public List<Sections> findByConfId(int id)
    {
        return repo.findByConfId(id);
    }

    public List<String> returnDeadline(int id)
    {
        return repo.returnDeadline(id);
    }
}
