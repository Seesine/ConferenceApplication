package model;

import java.util.Date;

/**
 * Created by Dragos on 5/8/2017.
 */
public class Conference
{
    int idConference;
    String name;
    int noParticipants;
    String deadline;


    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Conference(int idConference, String name, int noParticipants, String deadline) {
        this.idConference = idConference;
        this.name = name;
        this.noParticipants = noParticipants;
        this.deadline = deadline;

    }

    public Conference(String name, int noParticipants, String deadline) {
        this.name = name;
        this.noParticipants = noParticipants;
        this.deadline = deadline;

    }

    public Conference() {}

    @Override
    public String toString() {
        return "Conference{" +
                "idConference=" + idConference +
                ", name='" + name + '\'' +
                ", noParticipants=" + noParticipants +
                ", deadline=" + deadline +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }

    public int getNoParticipants() {
        return noParticipants;
    }

    public void setNoParticipants(int noParticipants) {
        this.noParticipants = noParticipants;
    }
}
