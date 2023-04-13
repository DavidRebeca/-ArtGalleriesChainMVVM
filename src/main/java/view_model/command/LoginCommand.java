package view_model.command;

import model.User;
import model.persistence.UserPersistence;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;
import view_model.LoginVM;

import javax.swing.*;

public class LoginCommand implements ICommand {
    private LoginVM vmLogin;

    public LoginCommand( LoginVM vmLogin) {
        this.vmLogin = vmLogin;
    }
    public void execute()
    {
        logIn();
    }
    public void logIn(){
        String userName = vmLogin.getUserField();
        User user=(new UserPersistence()).findByUsername(userName);
        if(user!=null){
            if(checkLogIn(user)){
                if(user.getUserType().equals("Admin"))
                    new AdminView();
                else
                   new EmployeeView(Integer.toString(user.getIdUser()));
            }
            else{
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Wrong username or password! Try again!");
                try {  new LoginView(); }
                catch (Exception e){}
            }
        }
        else  {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Wrong username or password! Try again!");
            try {  new LoginView(); }
            catch (Exception e){}
        }
    }

    public boolean checkLogIn(User user){
        String password = vmLogin.getPassField();
        if(password.equals(user.getPassword()))
            return true;
        return false;
    }



}
