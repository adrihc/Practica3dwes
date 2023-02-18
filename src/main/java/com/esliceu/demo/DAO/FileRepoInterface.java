package com.esliceu.demo.DAO;

import com.esliceu.demo.Model.DataFile;

public interface FileRepoInterface {
    void addFile(DataFile file);
    DataFile recoverFile(int objectId);
    void updateFile(DataFile dataFile);
}
