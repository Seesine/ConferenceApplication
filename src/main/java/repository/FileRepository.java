package repository;

import model.File;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikha on 5/13/2017.
 */
public class FileRepository {
    private Connection dbConnection;
    public FileRepository(Connection connection) {
        this.dbConnection = connection;
    }
    List<File> fileList = new ArrayList<File>();

    public void save(File f){
        fileList.add(f);
    }

    public List<File> getAll(){
        return fileList;
    }
}
