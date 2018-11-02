import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class payment {
    private JButton confirmButton;
    private JPanel mainPanel;
    private JTextArea textAreaAdress;
    private JTextArea textAreaOrder;

    public payment() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                database.connect();
                database.getUser(User.ID);
                database.getAdress(User.adress.get(0));
                showInfo();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Payment");
        payment form = new payment();
        frame.setContentPane(form.mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setVisible(true);
    }void showInfo(){
        textAreaAdress.setText(Adress.houseNumber+" "+Adress.villageNumber+" "+Adress.subDistrict+" "+Adress.district+" "+Adress.provine+" "+Adress.zipcode);
    }
}
