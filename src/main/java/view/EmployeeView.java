package view;

import model.User;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.BindValues;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import view_model.EmployeeVM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel idLabel;
    @Bind(value = "text", target = "myIdLabel.value", type = BindingType.BI_DIRECTIONAL)
    private JLabel myIdLabel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton guestButton;
    private JButton saveButton;
    private JButton sellButton;
    @Bind(value = "text", target = "format.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField formatText;

    String[] head = {"Id", "Name", "Artist", "Year", "Type", "Art Gallery", "Status"};
    Object[][] data = new Object[100][7];
    @BindValues({@Bind(value = "model", target = "model.value", type = BindingType.TARGET_TO_SOURCE),
            @Bind(value = "selectedRow", target = "row.value", type = BindingType.BI_DIRECTIONAL)})
    private JTable artWorksTable;

    EmployeeVM vm;

    public EmployeeView(String id) {

        vm=new EmployeeVM();
        frame = new JFrame("Employee");
        frame.setSize(840, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 227, 255));


        titleLabel = new JLabel("ART WORKS");
        titleLabel.setBounds(295, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        idLabel = new JLabel("My ID:");
        idLabel.setBounds(730, 2, 50, 30);
        idLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        myIdLabel = new JLabel(id);
        myIdLabel.setBounds(785, 2, 50, 30);
        myIdLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        titleLabel = new JLabel("ART WORKS");
        titleLabel.setBounds(295, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        formatText = new JTextField();
        formatText.setBounds(620, 45, 65, 30);
        formatText.setFont(new Font("Verdana", Font.BOLD, 15));
        formatText.setBackground(new Color(152, 178, 255));


        createButton = new JButton("Create");
        createButton.setBounds(10, 400, 120, 30);
        createButton.setFont(new Font("Verdana", Font.BOLD, 20));
        createButton.setBackground(new Color(100, 135, 242));

        updateButton = new JButton("Update");
        updateButton.setBounds(135, 400, 120, 30);
        updateButton.setFont(new Font("Verdana", Font.BOLD, 20));
        updateButton.setBackground(new Color(100, 135, 242));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(260, 400, 120, 30);
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 20));
        deleteButton.setBackground(new Color(100, 135, 242));

        guestButton = new JButton("Guest");
        guestButton.setBounds(690, 400, 120, 30);
        guestButton.setFont(new Font("Verdana", Font.BOLD, 20));
        guestButton.setBackground(new Color(100, 135, 242));

        saveButton = new JButton("Save");
        saveButton.setBounds(690, 45, 120, 30);
        saveButton.setFont(new Font("Verdana", Font.BOLD, 20));
        saveButton.setBackground(new Color(100, 135, 242));

        sellButton = new JButton("Sell");
        sellButton.setBounds(10, 45, 120, 30);
        sellButton.setFont(new Font("Verdana", Font.BOLD, 20));
        sellButton.setBackground(new Color(100, 135, 242));
        vm.getShowArtCommand().execute();
        artWorksTable=new JTable(data,head);
        artWorksTable.setFont(new Font("Verdana", Font.BOLD, 12));
        artWorksTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        artWorksTable.getTableHeader().setBackground(new Color(207, 210, 211));
        artWorksTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        artWorksTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        artWorksTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        JScrollPane scrollPane = new JScrollPane(artWorksTable);
        scrollPane.setBounds(10, 90, 805, 300);
        frame.add(scrollPane);

        try {
            Binder.bind(this, vm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        frame.setVisible(true);
        frame.add(updateButton);
        frame.add(titleLabel);
        frame.add(createButton);
        frame.add(deleteButton);
        frame.add(guestButton);
        frame.add(formatText);
        frame.add(saveButton);
        frame.add(sellButton);
        frame.add(idLabel);
        frame.add(myIdLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuestView();
                frame.dispose();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getInsertCommand().execute();
                frame.dispose();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getUpdateCommand().execute();
                frame.dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getDeleteCommand().execute();
                frame.dispose();
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getSellCommand().execute();
                frame.dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getSaveCommand().execute();
              //  frame.dispose();
            }
        });

    }

}