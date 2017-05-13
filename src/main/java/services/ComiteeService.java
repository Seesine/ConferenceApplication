package services;

import repository.CMRepository;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ComiteeService
{
    private CMRepository crepo;
    public ComiteeService (CMRepository crepo)
    {
        this.crepo = crepo;
    }
}
