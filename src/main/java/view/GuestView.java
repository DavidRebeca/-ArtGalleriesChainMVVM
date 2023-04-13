package view;

import net.sds.mvvm.bindings.BindValues;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import view_model.GuestVM;


public class GuestView  extends JFrame{

    private JFrame frame;
    private JLabel titleLabel;
    @Bind(value = "text", target = "filterType.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField filterTypeField;
    private JButton filterButton;
    @Bind(value = "text", target = "filterField.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField filterField;
    @Bind(value = "text", target = "searchField.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField searchField;
    private JButton loginButton;
    private JButton searchButton;

    String[] head = {"Id", "Name", "Artist", "Year", "Type", "Art Gallery", "Status"};
    Object[][] data = new Object[100][7];
    @BindValues({@Bind(value = "model", target = "model.value", type = BindingType.TARGET_TO_SOURCE),
            @Bind(value = "selectedRow", target = "row.value", type = BindingType.BI_DIRECTIONAL)})
    private JTable artWorksTable = new JTable(data, head);
    private GuestVM vm;


    public GuestView() {
        vm = new GuestVM();

        frame = new JFrame("Guest");
        frame.setSize(840, 550);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 227, 255));

        filterTypeField = new JTextField();
        filterTypeField.setBounds(10, 435, 120, 30);
        filterTypeField.setFont(new Font("Verdana", Font.BOLD, 15));
        filterTypeField.setBackground(new Color(152, 178, 255));

        filterField = new JTextField();
        filterField.setBounds(135, 435, 180, 30);
        filterField.setFont(new Font("Verdana", Font.BOLD, 15));
        filterField.setBackground(new Color(152, 178, 255));

        searchField = new JTextField();
        searchField.setBounds(10, 60, 300, 30);
        searchField.setFont(new Font("Verdana", Font.BOLD, 15));
        searchField.setBackground(new Color(152, 178, 255));

        titleLabel = new JLabel("ART WORKS");
        titleLabel.setBounds(320, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(690, 435, 120, 30);
        loginButton.setFont(new Font("Verdana", Font.BOLD, 20));
        loginButton.setBackground(new Color(100, 135, 242));

        filterButton = new JButton("FILTER");
        filterButton.setBounds(320, 435, 120, 30);
        filterButton.setFont(new Font("Verdana", Font.BOLD, 20));
        filterButton.setBackground(new Color(100, 135, 242));

        searchButton = new JButton("SEARCH");
        searchButton.setBounds(320, 60, 120, 30);
        searchButton.setFont(new Font("Verdana", Font.BOLD, 18));
        searchButton.setBackground(new Color(100, 135, 242));


        artWorksTable.setFont(new Font("Verdana", Font.BOLD, 12));
        artWorksTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        artWorksTable.getTableHeader().setBackground(new Color(207, 210, 211));
        artWorksTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        artWorksTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        artWorksTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        JScrollPane scrollPane = new JScrollPane(artWorksTable);
        scrollPane.setBounds(10, 105, 805, 300);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.add(filterTypeField);
        frame.add(titleLabel);
        frame.add(filterButton);
        frame.add(loginButton);
        frame.add(filterField);
        frame.add(searchField);
        frame.add(searchButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vm.getShowArtCommand().execute();
        try {
            Binder.bind(this, vm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginView();
                } catch (Exception exception) {
                }
                frame.dispose();
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getFilterCommand().execute();
                //frame.dispose();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               vm.getSearchCommand().execute();
            }
        });

    }

}
