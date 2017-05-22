package services;

import model.File;
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
}
