package view_model.command;

import model.ArtWork;
import model.User;
import model.persistence.ArtWorkPersistence;
import model.persistence.UserPersistence;
import view_model.TableVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowUsersCommand implements ICommand{
    private TableVM tableVM;

    public ShowUsersCommand( TableVM tableVM) {
        this.tableVM=tableVM;
    }
    @Override
    public void execute() {
        tableVM.getModel().get().clearData();
        List<User> users = (new UserPersistence()).readAll();
        for (User user : users) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(user.getIdUser()));
            row.add(user.getName());
            row.add(user.getUsername());
            row.add(user.getPassword());
            row.add(user.getUserType());
            row.add(user.getEmail());
            if(user.getArtGallery()!=null)
                row.add(user.getArtGallery().getName());
            tableVM.getModel().get().add(row);
        }

        for(int k= 100-users.size(); k>=1; k--){
            List<String> row = new ArrayList<>();
            for(int i=0; i<7;i++)
                row.add("");
            tableVM.getModel().get().add(row);
        }

    }
}
