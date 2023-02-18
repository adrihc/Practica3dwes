package com.esliceu.demo.Services;

import com.esliceu.demo.DAO.FileRepo;
import com.esliceu.demo.Model.Bucket;
import com.esliceu.demo.Model.DataFile;
import com.esliceu.demo.Model.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepo fileRepo;

    public DataFile createDataFile(Bucket bucket, MultipartFile file, Object object) throws IOException {
        DataFile df = new DataFile();
        df.setFileName(file.getOriginalFilename());
        df.setContentLength(file.getSize());
        df.setBody(file.getBytes());
        df.setVersion(1);
        df.setObject_id(object.getId());
        df.setExtension(file.getContentType());
        return df;
    }

    public void addFile(DataFile dataFile){
        fileRepo.addFile(dataFile);
    }

    public DataFile recoverFile(int objectId){
        return fileRepo.recoverFile(objectId);
    }

    public void updateFile(MultipartFile multipartFile,int id) throws IOException {
        DataFile dataFile = fileRepo.recoverFile(id);
        dataFile.setBody(multipartFile.getBytes());
        fileRepo.updateFile(dataFile);
    }

}
