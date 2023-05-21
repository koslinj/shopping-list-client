import shopping.Product;
import shopping.Products;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddProductSecondPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private String category;
    JButton back;
    JTextField textField;
    JButton countButton;
    JLabel infoLabel;
    private Products products;
    private Products list;
    private ArrayList<JButton> productButtons = new ArrayList<>();
    Product chosenProduct;

    AddProductSecondPanel(MyFrame frame, String cat) {
        myFrame = frame;
        category = cat;
        products = myFrame.getShoppingProducts();
        list = myFrame.getList();
        chosenProduct = null;

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerPanel.setBackground(Color.green);

        JPanel navbar = new JPanel();
        navbar.setLayout(new BorderLayout());
        navbar.setPreferredSize(new Dimension(100, 80));
        navbar.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setPreferredSize(new Dimension(100, 120));
        footer.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        back = new JButton("Wróć");
        back.setFont(new Font("Comic Sans", Font.BOLD, 20));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(110, 60));

        JLabel title = new JLabel("Produkty z kategorii " + category + ":", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans", Font.BOLD, 30));
        title.setBackground(Color.cyan);
        title.setOpaque(true);

        navbar.add(back, BorderLayout.WEST);
        navbar.add(title, BorderLayout.CENTER);

        textField = new JTextField();
        textField.setBounds(20, 40, 200, 40);
        textField.setFont(new Font("Comic Sans", Font.BOLD, 20));
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

            private void updateButtonState() {
                countButton.setEnabled(!textField.getText().isEmpty() && chosenProduct!=null);
            }
        });
        footer.add(textField);

        JLabel countLabel = new JLabel("Podaj ilość");
        countLabel.setBounds(60, 10, 200, 25);
        countLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        footer.add(countLabel);

        infoLabel = new JLabel();
        infoLabel.setBounds(45, 80, 200, 30);
        infoLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
        infoLabel.setForeground(Color.red);
        footer.add(infoLabel);

        ImageIcon icon = new ImageIcon("check.png");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);
        countButton = new JButton();
        countButton.setIcon(icon);
        countButton.setBounds(250, 30, 50, 50);
        countButton.setEnabled(false);
        countButton.setBackground(Color.white);
        countButton.addActionListener(this);
        footer.add(countButton);

        this.setLayout(new BorderLayout());
        this.add(innerPanel, BorderLayout.CENTER);
        this.add(navbar, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);


        for (Product p : products.getCategories().get(category)) {
            JButton button = new JButton();
            button.setText(p.getName());
            button.setActionCommand(p.getName());
            button.addActionListener(this);
            productButtons.add(button);
        }
        for (JButton b : productButtons) {
            b.setPreferredSize(new Dimension(250, 50));
            b.setFont(new Font("Comic Sans", Font.BOLD, 20));
            innerPanel.add(b);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Component[] arr = this.getParent().getComponents();
            for (Component comp : arr) {
                System.out.println(comp);
            }

            this.setVisible(false);

            Component previous = this.getParent().getComponent(1);
            myFrame.remove(this.getParent().getComponent(2));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }
        else if (e.getSource() == countButton) {
            try{
                String type = chosenProduct.getType();
                if(type.equals("sztuki")){
                    int val = Integer.parseInt(textField.getText());
                    Product newProd = new Product(chosenProduct.getName(), chosenProduct.getType(), val);
                    addingProcess(newProd);
                }
                else if(type.equals("litry") || type.equals("kilogramy")){
                    double val = Double.parseDouble(textField.getText());
                    Product newProd = new Product(chosenProduct.getName(), chosenProduct.getType(), val);
                    addingProcess(newProd);
                }
            }catch (Exception ex){
                System.out.println("Zła wartość liczbowa");
                infoLabel.setText("Zła wartość liczbowa");
            }

        }
        for(JButton b : productButtons){
            if(e.getSource()==b){
                for(JButton but : productButtons){
                    but.setForeground(Color.black);
                }
                b.setForeground(Color.red);
                break;
            }
        }
        for (Product p : products.getCategories().get(category)) {
            if (e.getActionCommand().equals(p.getName())) {
                System.out.println(p.getName());
                chosenProduct=p;
                if(textField.getText().length()>0) countButton.setEnabled(true);
            }
        }
    }

    private void addingProcess(Product p){
        list.addProduct(category,p,true);

        this.setVisible(false);

        Component[] arr = this.getParent().getComponents();
        for (Component comp : arr) {
            System.out.println(comp);
        }

        Component previous = this.getParent().getComponent(0);
        Component first = this.getParent().getComponent(1);
        Component second = this.getParent().getComponent(2);

        myFrame.remove(previous);
        previous.setVisible(true);
        myFrame.add(previous, BorderLayout.CENTER);
        myFrame.remove(first);
        myFrame.remove(second);
    }
}
