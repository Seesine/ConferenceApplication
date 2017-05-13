package services;

import repository.CMRepository;

/**
 * Created by Dragos on 5/8/2017.
 */
public class CMService
{
    private CMRepository crepo;
    public CMService(CMRepository crepo)
    {
        this.crepo = crepo;
    }
}
