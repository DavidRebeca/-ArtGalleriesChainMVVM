package view;

import model.User;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.BindValues;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import view_model.AdminVM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {
    private JFrame frame;
    private JLabel titleLabel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton guestButton;


    String[] head = {"Id","Name", "Username", "Password", "Type", "Email", "Art Gallery"};
    Object[][] data = new Object[150][7];
    @BindValues({@Bind(value = "model", target = "model.value", type = BindingType.TARGET_TO_SOURCE),
            @Bind(value = "selectedRow", target = "row.value", type = BindingType.BI_DIRECTIONAL)})
    private JTable usersTable=new JTable(data,head);

    private AdminVM vm;

    public AdminView(){
        vm=new AdminVM();
        frame = new JFrame("Admin");
        frame.setSize(840, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 227, 255 ));
        usersTable.setFont(new Font("Verdana", Font.BOLD, 12));
        usersTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
        usersTable.getTableHeader().setBackground(new Color(207, 210, 211));
        usersTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        usersTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(10, 70, 805, 300);
        frame.add(scrollPane);

        titleLabel = new JLabel("USERS", SwingConstants.CENTER);
        titleLabel.setBounds(295, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 25));

        createButton = new JButton("Create");
        createButton.setBounds(10, 400, 120, 30);
        createButton.setFont(new Font("Verdana", Font.BOLD, 20));
        createButton.setBackground(new Color(100, 135, 242  ));

        updateButton = new JButton("Update");
        updateButton.setBounds(135, 400, 120, 30);
        updateButton.setFont(new Font("Verdana", Font.BOLD, 20));
        updateButton.setBackground(new Color(100, 135, 242  ));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(260, 400, 120, 30);
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 20));
        deleteButton.setBackground(new Color(100, 135, 242  ));

        guestButton = new JButton("Guest");
        guestButton.setBounds(690, 400, 120, 30);
        guestButton.setFont(new Font("Verdana", Font.BOLD, 20));
        guestButton.setBackground(new Color(100, 135, 242  ));

        vm.getShowUsers().execute();
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
              // vm.setRow(usersTable.getSelectedRow());

               vm.getInsertCommand().execute();
               frame.dispose();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    vm.setRow(usersTable.getSelectedRow());
              vm.getUpdateCommand().execute();
              frame.dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  vm.setRow(usersTable.getSelectedRow());
              vm.getDeleteCommand().execute();
              frame.dispose();
            }
        });

    }

}
