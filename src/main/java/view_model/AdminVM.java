package view_model;

import model.MyTable;
import model.persistence.UserPersistence;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view_model.command.*;

import java.util.ArrayList;
import java.util.List;

public class AdminVM implements TableVM{
    private Property<MyTable> model;
    private Property<Integer> row = PropertyFactory.createProperty("row", this, Integer.class);
    private ICommand showUsers;
    private ICommand updateCommand;
    private ICommand insertCommand;
    private ICommand deleteCommand;
    private UserPersistence userPersistence;

    public AdminVM(){
        userPersistence=new UserPersistence();
        List<String> head = new ArrayList<>();
        head.add("Id");
        head.add("Name");
        head.add("Username");
        head.add("Password");
        head.add("Type");
        head.add("Email");
        head.add("Art Gallery");
        model = PropertyFactory.createProperty("model", this, new MyTable(head));
        row = PropertyFactory.createProperty("row", this, Integer.class);
        showUsers=new ShowUsersCommand(this);
        insertCommand=new AddUserCommand(this);
        updateCommand=new UpdateUserCommand(this);
        deleteCommand=new DeleteUserCommand(this);
    }

    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    @Override
    public Property<MyTable> getModel() {
        return this.model;
    }

    public Property<Integer> getRow() {
        return row;
    }

    public void setModel(Property<MyTable> model) {
        this.model = model;
    }

    public void setRow(Integer row) {
        this.row.set(row);
    }

    public ICommand getShowUsers() {
        return showUsers;
    }

    public ICommand getUpdateCommand() {
        return updateCommand;
    }

    public ICommand getInsertCommand() {
        return insertCommand;
    }

    public ICommand getDeleteCommand() {
        return deleteCommand;
    }
}
