package view_model;

import model.MyTable;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
import view_model.command.FilterCommand;
import view_model.command.ICommand;
import view_model.command.SearchCommand;
import view_model.command.ShowArtWorksCommand;

import java.util.ArrayList;
import java.util.List;

public class GuestVM implements TableVM {
    private Property<MyTable> model;
    private Property<Integer> row = PropertyFactory.createProperty("row", this, Integer.class);
    private Property<String> searchField;
    private Property<String> filterField;
    private Property<String> filterType;
    private ICommand showArt;
    private ICommand filterCommand;
    private ICommand searchCommand;

    public GuestVM() {
        List<String> head = new ArrayList<>();
        head.add("Id");
        head.add("Name");
        head.add("Artist");
        head.add("Year");
        head.add("Type");
        head.add("Art Gallery");
        head.add("Status");
        model = PropertyFactory.createProperty("model", this, new MyTable(head));
        searchField= PropertyFactory.createProperty("search", this, String.class);
        filterField= PropertyFactory.createProperty("search", this, String.class);
        searchField= PropertyFactory.createProperty("filter", this, String.class);
        filterType= PropertyFactory.createProperty("filterType", this, String.class);
        showArt = new ShowArtWorksCommand(this);
        filterCommand=new FilterCommand(this);
        searchCommand=new SearchCommand(this);
    }

    public Property<MyTable> getModel() {
        return model;
    }
    public ICommand getShowArtCommand() {
        return this.showArt;
    }

    public int getRow() {
        return row.get();
    }

    public ICommand getFilterCommand() {
        return filterCommand;
    }

    public ICommand getSearchCommand() {
        return searchCommand;
    }

    public Property<String> getSearchField() {
        return searchField;
    }

    public ICommand getShowArt() {
        return showArt;
    }

    public void setSearchField(String st) {
        this.searchField.set(st);
    }

    public Property<String> getFilterField() {
        return filterField;
    }

    public void setFilterField(String st) {
        this.filterField.set(st);
    }

    public Property<String> getFilterType() {
        return filterType;
    }
}
