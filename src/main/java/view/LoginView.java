package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import net.sds.mvvm.bindings.Bind;
import net.sds.mvvm.bindings.Binder;
import net.sds.mvvm.bindings.BindingType;
import view_model.LoginVM;

public class LoginView extends JFrame {

    private JFrame frame;
    private JLabel usernameLabel;
    private JLabel imgLabel;
    private JLabel passwordLabel;
    @Bind(value = "text", target = "userField.value", type = BindingType.BI_DIRECTIONAL)
    private JTextField usernameTextField;
    @Bind(value = "text", target = "passField.value", type = BindingType.BI_DIRECTIONAL)
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton guestButton;
    private LoginVM vm;

    public LoginView() throws IOException {

        frame = new JFrame("Login");
        frame.setSize(400, 300);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);

        BufferedImage image = ImageIO.read(new File("img/login7.png"));
        imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(50, 10, 300, 100);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 90, 125, 30);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 20));


        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 130, 120, 30);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        usernameTextField = new JTextField();
        usernameTextField.setBounds(155, 90, 190, 30);
        usernameTextField.setFont(new Font("Verdana", Font.BOLD, 20));

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(155, 130, 190, 30);
        passwordTextField.setFont(new Font("Verdana", Font.BOLD, 20));

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 180, 120, 30);
        loginButton.setFont(new Font("Verdana", Font.BOLD, 20));
        loginButton.setBackground(new Color(100, 135, 242));

        guestButton = new JButton("GUEST");
        guestButton.setBounds(140, 220, 120, 30);
        guestButton.setFont(new Font("Verdana", Font.BOLD, 20));
        guestButton.setBackground(new Color(100, 135, 242));

        frame.setVisible(true);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(usernameTextField);
        frame.add(passwordTextField);
        frame.add(loginButton);
        frame.add(guestButton);
        frame.add(imgLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vm = new LoginVM();

        try {
            Binder.bind(this, vm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GuestView();
               frame.dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.getLoginCommand().execute();
                frame.dispose();
            }
        });
    }

    }

