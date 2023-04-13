package view_model;

import model.MyTable;
import net.sds.mvvm.properties.Property;


public interface TableVM {
    Property<MyTable> getModel();
}
