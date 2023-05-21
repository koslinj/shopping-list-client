import shopping.Product;
import shopping.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveProductSecondPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private String category;
    JButton back;
    private Products list;
    private ArrayList<JButton> productButtons = new ArrayList<>();

    RemoveProductSecondPanel(MyFrame frame, String cat, Products typeOfList){
        myFrame = frame;
        category = cat;
        list = typeOfList;

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerPanel.setBackground(Color.green);

        JPanel navbar = new JPanel();
        navbar.setLayout(new BorderLayout());
        navbar.setPreferredSize(new Dimension(100,80));
        navbar.setBorder(BorderFactory.createLineBorder(Color.black,5));

        back = new JButton("Wróć");
        back.setFont(new Font("Comic Sans", Font.BOLD, 20));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(110,60));

        JLabel title = new JLabel("Produkty z kategorii " + category + ":", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans", Font.BOLD, 30));
        title.setBackground(Color.cyan);
        title.setOpaque(true);

        navbar.add(back, BorderLayout.WEST);
        navbar.add(title, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(innerPanel, BorderLayout.CENTER);
        this.add(navbar, BorderLayout.NORTH);

        for(Product p: list.getCategories().get(category)){
            JButton button = new JButton();
            button.setText(p.getName());
            button.setActionCommand(p.getName());
            button.addActionListener(this);
            productButtons.add(button);
        }
        for(JButton b : productButtons){
            b.setPreferredSize(new Dimension(250,50));
            b.setFont(new Font("Comic Sans", Font.BOLD, 20));
            innerPanel.add(b);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            Component[] arr = this.getParent().getComponents();
            for(Component comp : arr){
                System.out.println(comp);
            }

            this.setVisible(false);

            Component previous = this.getParent().getComponent(1);
            myFrame.remove(this.getParent().getComponent(2));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }

        for(Product p: list.getCategories().get(category)){
            if(e.getActionCommand().equals(p.getName())){
                System.out.println(p.getName());

                list.deleteProduct(category,p);

                this.setVisible(false);

                Component[] arr = this.getParent().getComponents();
                for(Component comp : arr){
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
                break;
            }
        }

    }
}
