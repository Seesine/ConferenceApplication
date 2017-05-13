package model;

import utils.AcceptLevel;

/**
 * Created by mikha on 5/13/2017.
 */
public class File {
    private int idF, idC;
    private String keywords, topic, filedoc;
    private AcceptLevel level;

    public File(int idF, String keywords, String topic, String filedoc, AcceptLevel level, int idC) {
        this.idF = idF;
        this.idC = idC;
        this.keywords = keywords;
        this.topic = topic;
        this.filedoc = filedoc;
        this.level = level;
    }

    public File() {
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFiledoc() {
        return filedoc;
    }

    public void setFiledoc(String filedoc) {
        this.filedoc = filedoc;
    }

    public AcceptLevel getLevel() {
        return level;
    }

    public void setLevel(AcceptLevel level) {
        this.level = level;
    }
}
