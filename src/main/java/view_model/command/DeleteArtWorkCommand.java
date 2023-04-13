package view_model.command;

import model.ArtGallery;
import model.ArtWork;
import model.User;
import model.persistence.ArtGalleryPersistence;
import model.persistence.ArtWorkPersistence;
import model.persistence.UserPersistence;
import view.EmployeeView;
import view_model.EmployeeVM;

import javax.swing.*;
import java.util.ArrayList;

public class DeleteArtWorkCommand implements ICommand{
    private EmployeeVM employeeVM;
    public DeleteArtWorkCommand(EmployeeVM vm){
        employeeVM=vm;
    }
    @Override
    public void execute() {
        int row =employeeVM.getRow().get();
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i]=employeeVM.getModel().get().getValueAt(row, i).toString();
        ArtWork artWork=(new ArtWorkPersistence()).findArtWorkById(Integer.parseInt(col[0]));
        (new ArtWorkPersistence()).delete(artWork);
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Delete done!!");
        User employee=(new UserPersistence()).findById(Integer.parseInt(employeeVM.getMyIdLabel().get()));
        new EmployeeView(Integer.toString(employee.getIdUser()));
    }
}
