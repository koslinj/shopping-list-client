import shopping.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveProductFirstPanel extends JPanel implements ActionListener {

    private MyFrame myFrame;
    private ArrayList<JButton> categoryButtons = new ArrayList<>();
    private Products list;
    private JButton back;

    RemoveProductFirstPanel(MyFrame frame, Products typeOfList){
        this.setLayout(new BorderLayout());
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerPanel.setBackground(Color.green);
        this.add(innerPanel, BorderLayout.CENTER);

        JPanel navbar = new JPanel();
        navbar.setLayout(new BorderLayout());
        navbar.setPreferredSize(new Dimension(100,80));
        navbar.setBorder(BorderFactory.createLineBorder(Color.black,5));

        back = new JButton("Wróć");
        back.setFont(new Font("Comic Sans", Font.BOLD, 20));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(110,60));

        JLabel title = new JLabel("Wybierz kategorię: ", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans", Font.BOLD, 30));
        title.setBackground(Color.cyan);
        title.setOpaque(true);

        navbar.add(back, BorderLayout.WEST);
        navbar.add(title, BorderLayout.CENTER);
        this.add(navbar, BorderLayout.NORTH);

        myFrame = frame;
        list = typeOfList;

        for(String c: list.getCategories().keySet()){
            JButton button = new JButton();
            button.setText(c);
            button.setActionCommand(c);
            button.addActionListener(this);
            categoryButtons.add(button);
        }
        for(JButton b : categoryButtons){
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

            Component previous = this.getParent().getComponent(0);
            myFrame.remove(this.getParent().getComponent(1));
            myFrame.remove(previous);
            previous.setVisible(true);
            myFrame.add(previous, BorderLayout.CENTER);
        }

        for(String c: list.getCategories().keySet()){
            if(e.getActionCommand().equals(c)){
                System.out.println(c);

                this.setVisible(false);

                RemoveProductSecondPanel removeProductSecondPanel = new RemoveProductSecondPanel(myFrame, c, list);
                removeProductSecondPanel.setVisible(true);
                myFrame.add(removeProductSecondPanel, BorderLayout.CENTER);
            }
        }
    }
}
