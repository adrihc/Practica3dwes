package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.Object;

import java.util.List;

public interface ObjectsRepoInterface {
    void addObject(Object object);
    List<Object> recoverObjects(int id);
    void delete(String name, int id);
    void updateObject(Object object);

}
