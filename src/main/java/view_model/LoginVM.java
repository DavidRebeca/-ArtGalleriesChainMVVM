package view_model;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view_model.command.ICommand;
import view_model.command.LoginCommand;

public class LoginVM {
    private Property<String> userField;
    private Property<String> passField;
    private ICommand loginCommand;

    public LoginVM(){
        userField= PropertyFactory.createProperty("username", this, String.class);
        passField = PropertyFactory.createProperty("password", this, String.class);
        loginCommand = new LoginCommand(this);
    }

    public ICommand getLoginCommand() {
        return loginCommand;
    }
    public String getUserField() {
        return userField.get();
    }
    public void setUserField(String userField) {
        this.userField.set(userField);
    }
    public String getPassField() {
        return passField.get();
    }
    public void setPassField(String passField) {
        this.passField.set(passField);
    }
}
