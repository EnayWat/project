import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class payment {
    private JButton confirmButton;
    private JPanel mainPanel;
    private JTextArea textAreaAdress;
    private JTable tabelCart;
    private JTable tableTotal;
    static JFrame frame = new JFrame("Payment");

    public payment() {
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Database.connect();
                Database.getUser(User.ID);
                Database.getAdress(User.adress.get(0));
                Cart.resetCart();
                Database.getProduct("2", 1);
                Database.getProduct("3", 2);
                showInfo();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.insertOrder();
                JOptionPane.showMessageDialog(null, "เพิ่มออร์เดอร์แล้ว");
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        payment form = new payment();
        frame.setContentPane(form.mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setVisible(true);
    }

    void showInfo() {
        textAreaAdress.setText(Adress.houseNumber + " " + Adress.villageNumber + " " + Adress.subDistrict + " " + Adress.district + " " + Adress.provine + " " + Adress.zipcode);
        DefaultTableModel modelCartTabel = new DefaultTableModel();
        modelCartTabel.addColumn("Name");
        modelCartTabel.addColumn("Price");
        modelCartTabel.addColumn("Amount");
        tabelCart.setModel(modelCartTabel);
        DefaultTableModel modelCartTabelRow = (DefaultTableModel) tabelCart.getModel();
        Object rowCartData[] = new Object[3];
        for (int i = 0; i < Cart.products.size(); i++) {
            rowCartData[0] = Cart.products.get(i).name;
            rowCartData[1] = Cart.products.get(i).price;
            rowCartData[2] = Cart.products.get(i).amount;
            modelCartTabelRow.addRow(rowCartData);
        }
        DefaultTableModel modelTotalTabel = new DefaultTableModel();
        modelTotalTabel.addColumn("Total");
        modelTotalTabel.addColumn("Price");
        modelTotalTabel.addColumn("Amount");
        tableTotal.setModel(modelTotalTabel);
        DefaultTableModel modelTotalTabelRow = (DefaultTableModel) tableTotal.getModel();
        Object rowTotalData[] = new Object[3];
        rowTotalData[0] = "Total";
        rowTotalData[1] = Cart.totalPrice;
        rowTotalData[2] = Cart.totalAmount;
        modelTotalTabelRow.addRow(rowTotalData);

    }
}
