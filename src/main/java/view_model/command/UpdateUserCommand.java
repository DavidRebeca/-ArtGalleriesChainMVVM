package view_model.command;

import model.User;
import model.persistence.ArtGalleryPersistence;
import model.persistence.UserPersistence;
import view.AdminView;
import view_model.AdminVM;

import javax.swing.*;
import java.util.ArrayList;

public class UpdateUserCommand implements ICommand {
    private AdminVM adminVM;

    public UpdateUserCommand(AdminVM adminVM) {
        this.adminVM = adminVM;
    }

    @Override
    public void execute() {
        UserPersistence userPersistence=adminVM.getUserPersistence();
        int row = adminVM.getRow().get();
        System.out.println(row);
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i] = adminVM.getModel().get().getValueAt(row, i).toString();
        User user = userPersistence.findById(Integer.parseInt(col[0]));
        ArtGalleryPersistence artGalleryPersistence = new ArtGalleryPersistence();
        if (!col[4].equals("Employee") && !col[4].equals("Admin"))
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "User type should be Employee or Admin!");
        else {
            user.setName(col[1]);
            user.setUsername(col[2]);
            user.setPassword(col[3]);
            user.setUserType(col[4]);
            user.setEmail(col[5]);
            if (col[6].equals("") && col[4].equals("Employee")) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Employee must work at a art gallery!");
                user = null;
            } else {
                if (!col[4].equals("Admin"))
                    if (artGalleryPersistence.findGalleryByName(col[6]) != null) {
                        user.setArtGallery(artGalleryPersistence.findGalleryByName(col[6]));
                    } else {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "This gallery does not exist in our chain!");
                        user = null;
                    }
            }
            if (user != null) {
                userPersistence.update(user);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Update done!");
            } else JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Update failed!");
        }
        new AdminView();
    }
}
