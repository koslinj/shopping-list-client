import shopping.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener {

    private JLabel title;
    private JButton addProduct;
    private JButton seeAllProducts;
    private JButton seeProductsFromCategory;
    private JButton removeAllProducts;
    private JButton removeProductsFromCategory;
    private JButton removeProduct;
    private JButton saveToFile;
    private JPanel editListOfProducts;
    private JPanel insideEditListOfProducts;
    private JButton addToList;
    private JButton removeFromList;
    private JButton saveListOfProducts;
    MyFrame myFrame;

    HomePanel(MyFrame frame){
        myFrame = frame;

        addProduct = new JButton("Dodaj produkt");
        seeAllProducts = new JButton("Wyświetl wszystkie produkty");
        seeProductsFromCategory = new JButton("Wyświetl produkty z kategorii");
        removeAllProducts = new JButton("Usuń wszystkie produkty");
        removeProductsFromCategory = new JButton("Usuń produkty z kategorii");
        removeProduct = new JButton("Usuń produkt");
        saveToFile = new JButton("Zapisz listę zakupów do pliku");
        editListOfProducts = new JPanel();
        editListOfProducts.setLayout(new BoxLayout(editListOfProducts, BoxLayout.Y_AXIS));
        insideEditListOfProducts = new JPanel();
        insideEditListOfProducts.setLayout(new BoxLayout(insideEditListOfProducts, BoxLayout.X_AXIS));
        addToList = new JButton("Dodaj");
        addToList.setFont(new Font("Comic Sans", Font.BOLD, 20));
        addToList.setMinimumSize(new Dimension(220,40));
        addToList.setPreferredSize(new Dimension(220,40));
        addToList.setMaximumSize(new Dimension(220,40));
        removeFromList = new JButton("Usuń");
        removeFromList.setFont(new Font("Comic Sans", Font.BOLD, 20));
        removeFromList.setMinimumSize(new Dimension(220,40));
        removeFromList.setPreferredSize(new Dimension(220,40));
        removeFromList.setMaximumSize(new Dimension(220,40));
        insideEditListOfProducts.add(addToList);
        insideEditListOfProducts.add(Box.createHorizontalGlue());
        insideEditListOfProducts.add(removeFromList);
        insideEditListOfProducts.setBackground(Color.green);
        saveListOfProducts = new JButton("Zapisz zmiany");
        JLabel desc = new JLabel("Edytuj listę dostępnych do zakupu");
        desc.setFont(new Font("Comic Sans", Font.BOLD, 22));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        editListOfProducts.add(desc);
        editListOfProducts.add(Box.createRigidArea(new Dimension(0,4)));
        editListOfProducts.add(insideEditListOfProducts);

        addProduct.addActionListener(this);
        seeAllProducts.addActionListener(this);
        seeProductsFromCategory.addActionListener(this);
        removeAllProducts.addActionListener(this);
        removeProductsFromCategory.addActionListener(this);
        removeProduct.addActionListener(this);
        saveToFile.addActionListener(this);
        addToList.addActionListener(this);
        removeFromList.addActionListener(this);
        saveListOfProducts.addActionListener(this);

        title = new JLabel("LISTA ZAKUPÓW");
        title.setFont(new Font("Comic Sans", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(0,6)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0,6)));
        addButton(addProduct);
        this.add(Box.createVerticalGlue());
        addButton(seeAllProducts);
        this.add(Box.createVerticalGlue());
        addButton(seeProductsFromCategory);
        this.add(Box.createVerticalGlue());
        addButton(removeAllProducts);
        this.add(Box.createVerticalGlue());
        addButton(removeProductsFromCategory);
        this.add(Box.createVerticalGlue());
        addButton(removeProduct);
        this.add(Box.createVerticalGlue());
        addButton(saveToFile);
        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(new Dimension(0,12)));
        addPanel(editListOfProducts);
        this.add(Box.createRigidArea(new Dimension(0,2)));
        addButton(saveListOfProducts);
        this.add(Box.createRigidArea(new Dimension(0,30)));
        this.setBackground(Color.green);
    }

    private void addButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Comic Sans", Font.BOLD, 20));
        button.setMinimumSize(new Dimension(450,40));
        button.setPreferredSize(new Dimension(450,40));
        button.setMaximumSize(new Dimension(450,40));
        this.add(button);
    }

    private void addPanel(JPanel panel) {
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBackground(Color.green);
        panel.setMinimumSize(new Dimension(450,75));
        panel.setPreferredSize(new Dimension(450,75));
        panel.setMaximumSize(new Dimension(450,75));
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addProduct){
            this.setVisible(false);

            AddProductFirstPanel addProductFirstPanel = new AddProductFirstPanel(myFrame);
            addProductFirstPanel.setVisible(true);
            myFrame.add(addProductFirstPanel, BorderLayout.CENTER);
            System.out.println("ADD");
        }
        else if(e.getSource()==seeAllProducts){
            this.setVisible(false);

            DisplayAllPanel displayAllPanel = new DisplayAllPanel(myFrame);
            displayAllPanel.setVisible(true);
            myFrame.add(displayAllPanel, BorderLayout.CENTER);
            System.out.println("DISPLAY ALL");
        }
        else if(e.getSource()==seeProductsFromCategory){
            this.setVisible(false);

            DisplayFromCategoryFirstPanel displayFromCategoryFirstPanel = new DisplayFromCategoryFirstPanel(myFrame);
            displayFromCategoryFirstPanel.setVisible(true);
            myFrame.add(displayFromCategoryFirstPanel, BorderLayout.CENTER);
            System.out.println("DISPLAY FROM CAT");
        }
        else if(e.getSource()==removeAllProducts){
            Products list = myFrame.getList();
            if(list.getCategories().size()==0){
                title.setText("LISTA ZAKUPÓW JEST PUSTA");
                title.setForeground(Color.red);

                Timer timer = new Timer(2000, x -> {
                    title.setText("LISTA ZAKUPÓW");
                    title.setForeground(Color.black);
                });
                timer.setRepeats(false);
                timer.start();
            }
            else{
                list.deleteAll();
                title.setText("USUNIĘTO WSZYSTKIE PRODUKTY");
                title.setForeground(Color.red);

                Timer timer = new Timer(2000, x -> {
                    title.setText("LISTA ZAKUPÓW");
                    title.setForeground(Color.black);
                });
                timer.setRepeats(false);
                timer.start();
            }

            System.out.println("REMOVE ALL");
        }
        else if(e.getSource()==removeProductsFromCategory){
            this.setVisible(false);

            RemoveFromCategoryPanel removeFromCategoryPanel = new RemoveFromCategoryPanel(myFrame);
            removeFromCategoryPanel.setVisible(true);
            myFrame.add(removeFromCategoryPanel, BorderLayout.CENTER);
            System.out.println("REMOVE FROM CAT");
        }
        else if(e.getSource()==removeProduct){
            this.setVisible(false);

            Products list = myFrame.getList();
            RemoveProductFirstPanel removeProductFirstPanel = new RemoveProductFirstPanel(myFrame, list);
            removeProductFirstPanel.setVisible(true);
            myFrame.add(removeProductFirstPanel, BorderLayout.CENTER);
            System.out.println("REMOVE ONE");
        }
        else if(e.getSource()==saveToFile){
            Products list = myFrame.getList();
            list.saveToFile();

            System.out.println("SAVE TO FILE");
        }
        else if(e.getSource()==addToList){
            this.setVisible(false);

            AddToListPanel addToListPanel = new AddToListPanel(myFrame);
            addToListPanel.setVisible(true);
            myFrame.add(addToListPanel, BorderLayout.CENTER);
            System.out.println("ADD TO LIST");
        }
        else if(e.getSource()==removeFromList){
            this.setVisible(false);

            Products products = myFrame.getShoppingProducts();
            RemoveProductFirstPanel removeProductFirstPanel = new RemoveProductFirstPanel(myFrame, products);
            removeProductFirstPanel.setVisible(true);
            myFrame.add(removeProductFirstPanel, BorderLayout.CENTER);
            System.out.println("REMOVE FROM LIST");
        }
        else if(e.getSource()==saveListOfProducts){
            Products products = myFrame.getShoppingProducts();
            products.updateListOfProducts();

            System.out.println("SAVE LIST OF PRODUCTS");
        }
    }
}
