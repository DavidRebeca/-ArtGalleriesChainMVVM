package view_model;

import model.MyTable;
import model.User;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import org.jboss.jandex.TypeTarget;
import view_model.command.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeVM implements TableVM{
    private Property<MyTable> model;
    private Property<Integer> row = PropertyFactory.createProperty("row", this, Integer.class);
    private Property<String> format = PropertyFactory.createProperty("format", this, String.class);
    private Property<String> myIdLabel;
    private ICommand showArt;
    private ICommand updateCommand;
    private ICommand insertCommand;
    private ICommand deleteCommand;
    private ICommand saveCommand;
    private ICommand sellCommand;


    public EmployeeVM(){
    List<String> head = new ArrayList<>();
        head.add("Id");
        head.add("Name");
        head.add("Artist");
        head.add("Year");
        head.add("Type");
        head.add("Art Gallery");
        head.add("Status");
        model = PropertyFactory.createProperty("model", this, new MyTable(head));
        myIdLabel=PropertyFactory.createProperty("id", this, String.class);
        showArt = new ShowArtWorksCommand(this);
        updateCommand=new UpdateArtWorkCommand(this);
        insertCommand=new AddArtWorkCommand(this);
        deleteCommand=new DeleteArtWorkCommand(this);
        sellCommand=new SellCommand(this);
        saveCommand=new SaveCommand(this);
    }
   @Override
    public Property<MyTable> getModel() {
        return model;
    }

    public ICommand getShowArtCommand() {
        return this.showArt;
    }

    public Property<Integer> getRow() {
        return row;
    }

    public ICommand getShowArt() {
        return showArt;
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

    public ICommand getSaveCommand() {
        return saveCommand;
    }

    public ICommand getSellCommand() {
        return sellCommand;
    }

    public Property<String> getMyIdLabel() {
        return myIdLabel;
    }

    public Property<String> getFormat() {
        return format;
    }
}
