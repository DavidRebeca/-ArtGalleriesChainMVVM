package model.persistence;

import model.ArtWork;
import model.User;

import java.util.ArrayList;

public class ArtWorkPersistence extends AbstractPersistence<ArtWork>{
    public ArtWork findArtWorkById(int id){
        ArrayList<ArtWork> artWorks= (ArrayList<ArtWork>) readAll();
        for(ArtWork art: artWorks){
            if(art.getIdArtWork()==id)
                return art;
        }
        return null;
    }
    public ArtWork findArtWorkByTitle(String title){
        ArrayList<ArtWork> artWorks= (ArrayList<ArtWork>) readAll();
        for(ArtWork art: artWorks){
            if(art.getName().equals(title))
                return art;
        }
        return null;
    }


}
