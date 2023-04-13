package view_model.command;

import model.ArtWork;
import model.persistence.ArtWorkPersistence;
import view_model.GuestVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchCommand implements ICommand{
    private GuestVM guestVM;
    public SearchCommand(GuestVM guestVM){
        this.guestVM=guestVM;
    }
    @Override
    public void execute() {
        String title=guestVM.getSearchField().get();
        ArtWork artWork= (new ArtWorkPersistence()).findArtWorkByTitle(title);
        if(artWork!=null){
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(artWork.getIdArtWork()));
            row.add(artWork.getName());
            row.add(artWork.getArtist());
            row.add(String.valueOf(artWork.getYear()));
            row.add(artWork.getType());
            row.add(artWork.getArtGallery().getName());
            row.add(artWork.getStatus());
            guestVM.getModel().get().clearData();
            guestVM.getModel().get().add(row);
            for(int k= 100; k>=1; k--){
                row = new ArrayList<>();
                for(int i=0; i<7;i++)
                    row.add("");
                guestVM.getModel().get().add(row);
            }
        }else guestVM.setSearchField("Art work not found!");

    }
}
