package fr.iut.td01.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.iut.td01.errors.ApiFileNotFoundException;

@Service
public class FileService {
    private String basePath;

    public FileService(@Value("${images_path}") String path){
        this.basePath = path;
    }

    public byte[] readFromPath() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/images/shrek.jpg");
        byte[] bytes = stream.readAllBytes();
        return bytes;
    }

    public byte[] readFromFilename(String filename) throws IOException {
        byte[] bytes=null;
        try{
            try(FileInputStream stream = new FileInputStream(Paths.get(this.basePath,filename).toString()))
            {
                bytes = stream.readAllBytes();
            }
        }
        catch(IOException ex){
            throw new ApiFileNotFoundException(filename);
        }
        return bytes;
    }

    public ArrayList<String> listFiles() {
        ArrayList<String> imageNames = new ArrayList<>();
        File folder = new File(basePath);
        File[] files = folder.listFiles();
        for(File file : files){
            if(file.isFile()){
                imageNames.add(file.getName());
            }
        }
        return imageNames;
    }
}

