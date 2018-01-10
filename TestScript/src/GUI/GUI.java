package GUI;

import Actions.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Cyn on 1/9/2018.
 */
public class GUI extends JFrame {
    private JButton actionButton = new JButton("Add Action"), startIfButton = new JButton("Begin If-Statement"), endIfButton = new JButton("End If-Block"), refreshButton = new JButton("Refresh"), removeButton = new JButton ("Remove Statement");
    private JButton saveButton = new JButton("Save"), loadButton = new JButton("Load");
    private JTextField file = new JTextField("");
    private File selectedFile = null;
    private JButton startButton = new JButton("Start");

    private JTextArea actionList;

    private NewActionGUI newAction;
    private ArrayList<Statement> actions;

    public GUI(ArrayList<Statement> actions)
    {
        this.actions = actions;
        newAction = new NewActionGUI(actions);

        setTitle("Script Creator");
        setLayout(new BorderLayout(12, 20));

        add(actionPanel(), BorderLayout.WEST);
        add(savePanel(), BorderLayout.EAST);
        add(startPanel(), BorderLayout.PAGE_END);

        this.revalidate();
        pack();

        addActionListeners();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateActionList()
    {
        actionList.setText("");
        for (int i = 0; i < actions.size(); i++)
        {
            actionList.append(i + ")" + actions.get(i).toString());
        }
    }

    private void addActionListeners() {
        startButton.addActionListener(o -> {
            this.setVisible(false);
        });

        saveButton.addActionListener(o -> {
            updateFile();
            saveContents();
        });

        loadButton.addActionListener(o -> {
            updateFile();
            loadContents();
        });


        actionButton.addActionListener(o -> {
            newAction.setVisible(true);
        });

        refreshButton.addActionListener(o -> {
            updateActionList();
        });
    }

    private void loadContents() {
    }

    private void saveContents() {
    }

    private void updateFile() {
        JFileChooser saver = new JFileChooser();
        int option = saver.showOpenDialog(GUI.this);

        if (option == JFileChooser.APPROVE_OPTION) {
            file.setText(saver.getSelectedFile().getName());
            selectedFile = saver.getSelectedFile();
        }
    }

    private JPanel startPanel() {
        JPanel start = new JPanel();

        start.add(startButton);

        return start;
    }

    private JPanel savePanel() {
        JPanel save = new JPanel();
        save.setLayout(new GridLayout(2, 1, 20, 5));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 1, 20, 5));
        buttons.add(saveButton);
        buttons.add(loadButton);


        save.add(buttons);

        JPanel chosen = new JPanel();
        chosen.setLayout(new FlowLayout(FlowLayout.LEFT));
        chosen.add(new JLabel("File chosen: "));
        file.setColumns(8);
        chosen.add(file);

        save.add(chosen);

        return save;
    }

    private JPanel actionPanel() {
        JPanel actPan = new JPanel();
        actPan.setLayout(new BorderLayout(20, 0));

        actionList = new JTextArea(18, 40);
        actionList.setEditable(false);

        JScrollPane scroll = new JScrollPane(actionList);
        actionList.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
        actPan.add(scroll, BorderLayout.WEST);

        JPanel actionListButtons = new JPanel();
        actionListButtons.setLayout(new GridLayout(5, 1, 0, 20));
        actionListButtons.add(actionButton);
        actionListButtons.add(startIfButton);
        actionListButtons.add(endIfButton);
        actionListButtons.add(refreshButton);
        actionListButtons.add(removeButton);

        actPan.add(actionListButtons);

        return actPan;
    }

}
