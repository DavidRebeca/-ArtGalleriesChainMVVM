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

public class AddArtWorkCommand implements ICommand{
    private EmployeeVM employeeVM;
    public AddArtWorkCommand(EmployeeVM vm){
        employeeVM=vm;
    }
    @Override
    public void execute() {
        int row =employeeVM.getRow().get();
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i]=employeeVM.getModel().get().getValueAt(row, i).toString();
        ArrayList<ArtWork> artWorks = (ArrayList<ArtWork>) (new ArtWorkPersistence()).readAll();
        ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
        if(artGalleryPersistence.findGalleryByName(col[5])!=null) {
            ArtGallery artGallery = artGalleryPersistence.findGalleryByName (col[5]);
            ArtWork artWork = new ArtWork((artWorks.size() + 1), col[1], col[2], Integer.parseInt(col[3]), col[4], artGallery,"available");
            (new ArtWorkPersistence()).insert(artWork);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Art work was inserted!!");
        }else JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Art Gallery invalid!");
        User employee=(new UserPersistence()).findById(Integer.parseInt(employeeVM.getMyIdLabel().get()));
        new EmployeeView(Integer.toString(employee.getIdUser()));
    }
}
