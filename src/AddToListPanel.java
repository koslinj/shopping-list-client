import shopping.Product;
import shopping.Products;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddToListPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private Products products;
    private JButton back;
    private JButton confirm;
    private JTextField catField;
    private JTextField nameField;
    private JComboBox<String> typeComboBox;

    AddToListPanel(MyFrame frame){
        myFrame = frame;
        products = myFrame.getShoppingProducts();

        back = new JButton("Wróć");
        back.setFont(new Font("Comic Sans", Font.BOLD, 20));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(110,60));

        catField = new JTextField();
        nameField = new JTextField();

        confirm = new JButton("Zatwierdź");
        confirm.addActionListener(this);
        confirm.setEnabled(false);

        DocumentListener documentListener = new DocumentListener() {
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
                confirm.setEnabled(!nameField.getText().isEmpty() && !catField.getText().isEmpty());
            }
        };

        nameField.getDocument().addDocumentListener(documentListener);
        catField.getDocument().addDocumentListener(documentListener);

        JLabel title = new JLabel("Nowy produkt", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans", Font.BOLD, 30));
        title.setBackground(Color.cyan);
        title.setOpaque(true);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(Color.green);

        JPanel navbar = new JPanel();
        navbar.setLayout(new BorderLayout());
        navbar.setPreferredSize(new Dimension(100,60));
        navbar.setBackground(Color.green);
        navbar.setBorder(BorderFactory.createLineBorder(Color.black,5));
        navbar.add(back, BorderLayout.WEST);
        navbar.add(title, BorderLayout.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.green);

        JLabel catLabel = new JLabel("Wpisz kategorię");
        JLabel nameLabel = new JLabel("Wpisz nazwę");
        JLabel typeLabel = new JLabel("Wybierz typ");
        String[] types = {"sztuki", "litry", "kilogramy"};
        typeComboBox = new JComboBox<String>(types);

        catField.setMinimumSize(new Dimension(350,40));
        catField.setPreferredSize(new Dimension(350,40));
        catField.setMaximumSize(new Dimension(350,40));
        nameField.setMinimumSize(new Dimension(350,40));
        nameField.setPreferredSize(new Dimension(350,40));
        nameField.setMaximumSize(new Dimension(350,40));
        typeComboBox.setMinimumSize(new Dimension(300,40));
        typeComboBox.setPreferredSize(new Dimension(300,40));
        typeComboBox.setMaximumSize(new Dimension(300,40));

        catLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        typeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);

        catLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        nameLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        typeLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        catField.setFont(new Font("Comic Sans", Font.BOLD, 18));
        nameField.setFont(new Font("Comic Sans", Font.BOLD, 18));
        typeComboBox.setFont(new Font("Comic Sans", Font.BOLD, 18));
        confirm.setFont(new Font("Comic Sans", Font.BOLD, 20));

        innerPanel.add(Box.createRigidArea(new Dimension(10,30)));
        innerPanel.add(catLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(10,5)));
        innerPanel.add(catField);
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(nameLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(10,5)));
        innerPanel.add(nameField);
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(typeLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(10,5)));
        innerPanel.add(typeComboBox);
        innerPanel.add(Box.createVerticalGlue());
        innerPanel.add(confirm);
        innerPanel.add(Box.createRigidArea(new Dimension(10,100)));

        this.setLayout(new BorderLayout());
        this.add(innerPanel, BorderLayout.CENTER);
        this.add(navbar, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            Component[] arr = this.getParent().getComponents();
            for(Component comp : arr){
                System.out.println(comp);
            }

            this.setVisible(false);

            Component previous = this.getParent().getComponent(0);
            myFrame.remove(this.getParent().getComponent(1));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }

        else if(e.getSource()==confirm){
            String type = (String) typeComboBox.getSelectedItem();
            Product prod = new Product(nameField.getText(), type, 0);
            products.addProduct(catField.getText(), prod,true);

            this.setVisible(false);

            Component previous = this.getParent().getComponent(0);
            myFrame.remove(this.getParent().getComponent(1));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }
    }
}
