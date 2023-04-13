package view_model.command;

import model.ArtWork;
import model.User;
import model.persistence.ArtGalleryPersistence;
import model.persistence.ArtWorkPersistence;
import model.persistence.UserPersistence;
import view.EmployeeView;
import view_model.EmployeeVM;

import javax.swing.*;

public class UpdateArtWorkCommand implements ICommand{
    private EmployeeVM employeeVM;
    public UpdateArtWorkCommand(EmployeeVM vm){
        employeeVM=vm;
    }
    @Override
    public void execute() {
        int row =employeeVM.getRow().get();
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i]=employeeVM.getModel().get().getValueAt(row, i).toString();
        ArtWork artWork=(new ArtWorkPersistence()).findArtWorkById(Integer.parseInt(col[0]));
        artWork.setName(col[1]);
        artWork.setArtist(col[2]);
        artWork.setYear(Integer.parseInt(col[3]));
        artWork.setType(col[4]);
        artWork.setStatus(col[6]);
        ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
        if(artGalleryPersistence.findGalleryByName(col[5])!=null) {
            artWork.setArtGallery(artGalleryPersistence.findGalleryByName(col[5]));
            (new ArtWorkPersistence()).update(artWork);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Update done!!");
        }else JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Art Gallery invalid!");
        User employee=(new UserPersistence()).findById(Integer.parseInt(employeeVM.getMyIdLabel().get()));
        new EmployeeView(Integer.toString(employee.getIdUser()));
    }
}
