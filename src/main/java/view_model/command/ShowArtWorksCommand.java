package view_model.command;

import model.ArtWork;
import model.persistence.ArtWorkPersistence;
import view_model.TableVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowArtWorksCommand implements ICommand {
    private TableVM tableVM;

    public ShowArtWorksCommand( TableVM tableVM) {
        this.tableVM=tableVM;
    }
    @Override
    public void execute() {
        tableVM.getModel().get().clearData();
        List<ArtWork> sections = (new ArtWorkPersistence()).readAll();
        System.out.println(sections.size());
        Collections.sort(sections);
        List<String> row;
        for (ArtWork artWork : sections) {
             row = new ArrayList<>();
            row.add(Integer.toString(artWork.getIdArtWork()));
            row.add(artWork.getName());
            row.add(artWork.getArtist());
            row.add(String.valueOf(artWork.getYear()));
            row.add(artWork.getType());
            row.add(artWork.getArtGallery().getName());
            row.add(artWork.getStatus());
            tableVM.getModel().get().add(row);
        }
        for(int k= 100-sections.size(); k>=1; k--){
           row = new ArrayList<>();
            for(int i=0; i<7;i++)
                row.add("");
            tableVM.getModel().get().add(row);
        }

    }
}
