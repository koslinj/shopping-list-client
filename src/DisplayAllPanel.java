import shopping.Product;
import shopping.Products;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private Products list;
    private JButton back;

    DisplayAllPanel(MyFrame frame){
        myFrame = frame;

        list = myFrame.getList();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(Color.green);

        back = new JButton("Wróć");
        back.setFont(new Font("Comic Sans", Font.BOLD, 20));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(85,60));
        back.setAlignmentY(TOP_ALIGNMENT);
        this.add(back);

        this.add(Box.createRigidArea(new Dimension(10,0)));
        int i=0;
        for(String c: list.getCategories().keySet()){
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
            JLabel title = new JLabel(c);
            title.setBackground(Color.CYAN);

            Border border = BorderFactory.createLineBorder(Color.black,2);
            Border margin = BorderFactory.createEmptyBorder(6,6,6,6);
            title.setBorder(BorderFactory.createCompoundBorder(border, margin));

            title.setOpaque(true);
            title.setFont(new Font("Comic Sans", Font.BOLD, 20));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryPanel.add(title);
            categoryPanel.add(Box.createRigidArea(new Dimension(0,8)));
            for(Product p: list.getCategories().get(c)){
                JLabel prod = new JLabel(p.getName());
                prod.setFont(new Font("Comic Sans", Font.ITALIC, 16));
                prod.setAlignmentX(Component.CENTER_ALIGNMENT);
                prod.setBackground(Color.white);
                prod.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
                prod.setOpaque(true);

                categoryPanel.add(Box.createRigidArea(new Dimension(0,12)));
                categoryPanel.add(prod);
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
                prodInfo.setFont(new Font("Comic Sans", Font.PLAIN, 15));
                prodInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
                categoryPanel.add(Box.createRigidArea(new Dimension(0,2)));
                categoryPanel.add(prodInfo);
            }
            categoryPanel.setAlignmentY(TOP_ALIGNMENT);
            categoryPanel.setBackground(Color.green);
            this.add(categoryPanel);

            if(i == list.getCategories().keySet().size()-1){
                this.add(Box.createRigidArea(new Dimension(30,0)));
            }
            else{
                this.add(Box.createHorizontalGlue());
            }
            i++;
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

            Component previous = this.getParent().getComponent(0);
            myFrame.remove(this.getParent().getComponent(1));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }
    }
}
