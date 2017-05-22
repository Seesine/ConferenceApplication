package model;

import utils.AcceptLevel;

import java.io.Serializable;

/**
 * Created by mikha on 5/13/2017.
 */
public class File implements Serializable{
    private int idF, idses, reviewCount;
    private String keywords, topic, filedoc;
    private String level;
    private String abstractData;

    public File(int idF, String keywords, String topic, String filedoc, String level, int idses, int reviewCount, String abstractData) {
        this.idF = idF;
        this.idses = idses;
        this.keywords = keywords;
        this.topic = topic;
        this.filedoc = filedoc;
        this.level = level;
        this.reviewCount = reviewCount;
        this.abstractData = abstractData;
    }

    public File() {
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public int getIdses() {
        return idses;
    }

    public void setIdses(int idC) {
        this.idses = idses;
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



    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getAbstractData() {
        return abstractData;
    }

    public void setAbstractData(String abstractData) {
        this.abstractData = abstractData;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
