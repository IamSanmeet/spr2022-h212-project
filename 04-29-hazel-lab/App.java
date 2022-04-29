 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);

        // first node is black
        RBTViewer<Integer> bstViewer
                = new RBTViewer<Integer>(new Node<>(new DrawableNode<>(50),
                                                    new Leaf<>(), new Leaf<>(), RBT.RBTColor.BLACK));
        JPanel topBar = new JPanel();
        JSpinner insertInput = new JSpinner(new SpinnerNumberModel());
        JButton insertButton = new JButton("Insert");
        JSpinner removeInput = new JSpinner(new SpinnerNumberModel());
        JButton removeButton = new JButton("Remove");

        JComponent insertInputEditor = insertInput.getEditor();
        JFormattedTextField ctf = ((JSpinner.DefaultEditor) insertInputEditor).getTextField();
        ctf.setColumns(10);
        JComponent removeInputEditor = removeInput.getEditor();
        JFormattedTextField itf = ((JSpinner.DefaultEditor) removeInputEditor).getTextField();
        itf.setColumns(10);

        ActionListener insert = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                bstViewer.insert(new DrawableNode<Integer>((Integer) insertInput.getValue()));
            }
        };
        ActionListener remove = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                bstViewer.remove(new DrawableNode<Integer>((Integer) removeInput.getValue()));
            }
        };

        insertButton.addActionListener(insert);
        removeButton.addActionListener(remove);

        topBar.add(insertInput);
        topBar.add(insertButton);
        topBar.add(removeInput);
        topBar.add(removeButton);

        frame.getContentPane().add(BorderLayout.NORTH, topBar);
        frame.getContentPane().add(BorderLayout.CENTER, bstViewer);
        frame.setVisible(true);
    }
}