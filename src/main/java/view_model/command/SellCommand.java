package view_model.command;

import model.ArtWork;
import model.User;
import model.persistence.ArtGalleryPersistence;
import model.persistence.ArtWorkPersistence;
import model.persistence.UserPersistence;
import view.EmployeeView;
import view_model.EmployeeVM;

import javax.swing.*;

public class SellCommand implements ICommand{
    private EmployeeVM employeeVM;
    public SellCommand(EmployeeVM employeeVM){
        this.employeeVM=employeeVM;
    }
    @Override
    public void execute() {
        int row =employeeVM.getRow().get();
        String[] col = new String[7];
        for (int i = 0; i < 7; i++)
            col[i]=employeeVM.getModel().get().getValueAt(row, i).toString();
        User employee=(new UserPersistence()).findById(Integer.parseInt(employeeVM.getMyIdLabel().get()));
        if(employee.getArtGallery().getName().equals(col[5])){
            ArtWork artWork=(new ArtWorkPersistence()).findArtWorkById(Integer.parseInt(col[0]));
            artWork.setName(col[1]);
            artWork.setArtist(col[2]);
            artWork.setYear(Integer.parseInt(col[3]));
            artWork.setType(col[4]);
            artWork.setStatus("selled");
            ArtGalleryPersistence artGalleryPersistence=new ArtGalleryPersistence();
            artWork.setArtGallery(artGalleryPersistence.findGalleryByName(col[5]));
            (new ArtWorkPersistence()).update(artWork);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Art work selled!!");
            }else JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"You can't sell art works from this gallery! Your art gallery is "+employee.getArtGallery().getName());
        new EmployeeView(Integer.toString(employee.getIdUser()));
    }


}

