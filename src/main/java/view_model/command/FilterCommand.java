package view_model.command;

import model.ArtWork;
import model.persistence.ArtWorkPersistence;
import view_model.GuestVM;
import view_model.LoginVM;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterCommand implements ICommand{

    private GuestVM guestVM;
    public FilterCommand(GuestVM guestVM){
        this.guestVM=guestVM;
    }
    @Override
    public void execute() {
        String type = guestVM.getFilterType().get();
        if(type.equals("Type")||type.equals("Artist")){
        String filter=guestVM.getFilterField().get();
        List<ArtWork> sections = (new ArtWorkPersistence()).readAll();
        int cont = 0;
        for (ArtWork artWork : sections) {
            if ((type.equals("Type")&&artWork.getType().equals(filter))||(type.equals("Artist")&&artWork.getArtist().equals(filter))) {
                if(cont==0)
                    guestVM.getModel().get().clearData();
                cont++;
                List<String> row = new ArrayList<>();
                row.add(Integer.toString(artWork.getIdArtWork()));
                row.add(artWork.getName());
                row.add(artWork.getArtist());
                row.add(String.valueOf(artWork.getYear()));
                row.add(artWork.getType());
                row.add(artWork.getArtGallery().getName());
                row.add(artWork.getStatus());
                guestVM.getModel().get().add(row);
            }

        }
        if(cont!=0)
            for (int k = 100 - cont; k >= 1; k--) {
                List<String> row = new ArrayList<>();
                for (int i = 0; i < 7; i++)
                    row.add("");
                guestVM.getModel().get().add(row);
            }
        else{
            guestVM.setFilterField("Not found!");
        }
    }else
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Filter should be Type or Artist!");
        }
    }

}
