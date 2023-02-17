package com.esliceu.demo.Model;

import java.sql.Time;
import java.util.Date;

public class Object {
    int id;
    String name;
    String description;
    String owner;
    int bucket_id;
    int version;
    Date lastModified;
    Time lastModifiedTime;

    public Time getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Time lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBucket_id() {
        return bucket_id;
    }

    public void setBucket_id(int bucket_id) {
        this.bucket_id = bucket_id;
    }
}