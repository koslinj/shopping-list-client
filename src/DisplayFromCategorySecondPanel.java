import shopping.Product;
import shopping.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayFromCategorySecondPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private String category;
    JButton back;
    private Products list;
    private ArrayList<JPanel> productPanels = new ArrayList<>();

    DisplayFromCategorySecondPanel(MyFrame frame, String cat){
        myFrame = frame;
        category = cat;
        list = myFrame.getList();

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
            JLabel prod = new JLabel(p.getName(), SwingConstants.CENTER);
            prod.setFont(new Font("Comic Sans", Font.ITALIC, 22));
            prod.setHorizontalTextPosition(SwingConstants.CENTER);
            prod.setAlignmentX(Component.CENTER_ALIGNMENT);
            prod.setBackground(Color.white);
            prod.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            prod.setOpaque(true);

            JLabel prodInfo = new JLabel();
            if(p.getType().equals("litry")){
                prodInfo.setText(p.getCount() + " l");
            }
            else if(p.getType().equals("kilogramy")){
                prodInfo.setText(p.getCount() + " kg");
            }
            else if(p.getType().equals("sztuki")){
                prodInfo.setText((int) p.getCount() + " sztuki");
            }
            prodInfo.setFont(new Font("Comic Sans", Font.PLAIN, 18));
            prodInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel prodPanel = new JPanel();
            prodPanel.setBackground(Color.green);
            prodPanel.setLayout(new BoxLayout(prodPanel, BoxLayout.Y_AXIS));
            prodPanel.add(prod);
            prodPanel.add(prodInfo);
            productPanels.add(prodPanel);
        }
        for(JPanel p : productPanels){
            innerPanel.add(p);
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

    }
}
