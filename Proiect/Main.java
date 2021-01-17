import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static JFrame frame;
    private static JLabel GenLabel;
    private static JTextField tGen;
    private static JLabel ScriitorLabel;
    private static JTextField tScriitor;
    private static JLabel anLabel;
    private static JTextField tAn;
    private static JLabel NumarPaginiLabel;
    private static JTextField tNumarPagini;
    private static JButton save;
    private static JButton update;
    private static JButton modify;
    private static JButton delete;
    private static JList jList;
    private static DefaultListGen listGen = new DefaultListGen();

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(700, 500));
        JPanel container = new JPanel();
        frame.add(container);
        JToolBar toolBar = new JToolBar();

        toolBarFeatures(toolBar);
        addNewObjects(container);
        showAllObjects(container);

        frame.add(toolBar, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    private static void toolBarFeatures(JToolBar toolBar) {
        JButton addNewObject = new JButton("Add new object");
        addNewObject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jList.setVisible(false);

                modify.setVisible(false);
                delete.setVisible(false);

                setVisibilityForAddNewObject(true);
                System.out.println("mouse clicked");
            }
        });
        toolBar.add(addNewObject);

        JButton listAllObjects = new JButton("List objects");
        listAllObjects.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                jList.setVisible(true);

                modify.setVisible(true);
                delete.setVisible(true);
                update.setVisible(false);
                setVisibilityForAddNewObject(false);
                System.out.println("mouse clicked");
            }
        });

        toolBar.add(listAllObjects);

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carte> carti = new ArrayList<>();
                for (int i = 0; i < listGen.size(); i++)
                    carti.add((Carte) listGen.get(i));
                FileUtility.writeToFile(carti);
                System.exit(0);
            }
        });
        toolBar.add(exit);
    }

    private static void showAllObjects(JPanel container) {
        jList = new JList(listGen);
        List<Carte> carti = FileUtility.readFromFile();
        for (Carte Carte : carti)
            listGen.addElement(Carte);
        container.add(jList);
        jList.setCellRenderer(createListRenderer());
        jList.setVisible(false);

        modify = new JButton("Modifica");
        container.add(modify);
        modify.setVisible(false);
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Carte Carte = (Carte) listGen.get(jList.getSelectedIndex());
                update.setVisible(true);
                setVisibilityForAddNewObject(true);
                jList.setVisible(false);
                modify.setVisible(false);
                delete.setVisible(false);
                save.setVisible(false);
                tGen.setText(Carte.getGen());
                tScriitor.setText(Carte.getScriitor());
                tNumarPagini.setText(Carte.getNumarPagini());
                tAn.setText(Carte.getAn() + "");
            }
        });
        delete = new JButton("Sterge");
        container.add(delete);
        delete.setVisible(false);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jList.getSelectedIndex() != -1)
                    listGen.remove(jList.getSelectedIndex());
            }
        });
    }

    private static void addNewObjects(JPanel container) {

        GenLabel = new JLabel("Gen Carte:");
        container.add(GenLabel);

        tGen = new JTextField(20);
        container.add(tGen);

        ScriitorLabel = new JLabel("Scriitorul :");
        container.add(ScriitorLabel);

        tScriitor = new JTextField(20);
        container.add(tScriitor);

        NumarPaginiLabel = new JLabel("Numar pagini:");
        container.add(NumarPaginiLabel);

        tNumarPagini = new JTextField(20);
        container.add(tNumarPagini);

        anLabel = new JLabel("An aparitie:");
        container.add(anLabel);

        tAn = new JTextField(20);
        container.add(tAn);

        save = new JButton("Save");
        container.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Gen = tGen.getText();
                String Scriitor = tScriitor.getText();
                String NumarPagini = tNumarPagini.getText();
                int an = Integer.parseInt(tAn.getText());
                Carte Carte = new Carte(Gen, Scriitor, NumarPagini, an);
                listGen.addElement(Carte);
                tGen.setText("");
                tScriitor.setText("");
                tNumarPagini.setText("");
                tAn.setText("");
                JOptionPane.showMessageDialog(null, "O noua carte a fost inregistrata");
            }
        });

        update = new JButton("Actualizeaza");
        container.add(update);
        update.setVisible(false);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String Gen = tGen.getText();
                String Scriitor = tScriitor.getText();
                String NumarPagini = tNumarPagini.getText();
                int an = Integer.parseInt(tAn.getText());
                Carte Carte = new Carte(Gen, Scriitor, NumarPagini, an);
                listGen.set(jList.getSelectedIndex(), Carte);
                tGen.setText("");
                tScriitor.setText("");
                tNumarPagini.setText("");
                tAn.setText("");
                JOptionPane.showMessageDialog(null, "Carte-a a fost modificata cu succes");

            }
        });

    }

    private static void setVisibilityForAddNewObject(boolean b) {
        GenLabel.setVisible(b);
        tGen.setVisible(b);
        ScriitorLabel.setVisible(b);
        tScriitor.setVisible(b);
        NumarPaginiLabel.setVisible(b);
        tNumarPagini.setVisible(b);
        anLabel.setVisible(b);
        tAn.setVisible(b);
        save.setVisible(b);
    }

    private static ListCellRenderer<? super Carte> createListRenderer() {
        return new DefaultListCellRenderer() {
            private Color background = new Color(0, 100, 255, 15);
            private Color defaultBackground = (Color) UIManager.get("List.background");

            @Override
            public Feature getListCellRendererFeature(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Feature c = super.getListCellRendererFeature(list, value, index, isSelected, cellHasFocus);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    Carte Carte = (Carte) value;
                    label.setText(Carte.getGen() + "/" + Carte.getScriitor() + "/" + Carte.getNumarPagini() + "/" + Carte.getAn());
                    if (!isSelected) {
                        label.setBackground(index % 2 == 8 ? background : defaultBackground);
                    }
                }
                return c;
            }
        };
    }
}

