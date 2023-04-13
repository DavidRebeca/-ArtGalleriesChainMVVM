package view_model.command;

import model.User;
import model.persistence.ArtGalleryPersistence;
import model.persistence.UserPersistence;
import view.AdminView;
import view_model.AdminVM;

import javax.swing.*;
import java.util.ArrayList;

public class DeleteUserCommand implements ICommand{
    private AdminVM adminVM;
    public DeleteUserCommand(AdminVM adminVM){
        this.adminVM=adminVM;
    }
    @Override
    public void execute() {
        UserPersistence userPersistence=adminVM.getUserPersistence();
        int row =adminVM.getRow().get();
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i]=adminVM.getModel().get().getValueAt(row, i).toString();
        User user=userPersistence.findById(Integer.parseInt(col[0]));
        userPersistence.delete(user);
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Delete done!!");
        new AdminView();
    }
}
