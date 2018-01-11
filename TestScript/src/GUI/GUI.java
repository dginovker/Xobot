package GUI;

import Actions.Action;
import GUI.MainPanels.ActionPanel;
import GUI.NewGuis.NewActionGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Cyn on 1/9/2018.
 */
public class GUI extends JFrame {
    private JButton saveButton = new JButton("Save"), loadButton = new JButton("Load");
    private JTextField file = new JTextField("");
    private File selectedFile = null;
    private JButton startButton = new JButton("Start");

    private JTextArea actionList = new JTextArea(18, 40);

    private NewActionGUI newAction;
    private ArrayList<Action> actions;

    public GUI(ArrayList<Action> actions)
    {
        this.actions = actions;

        Consumer<Integer> updateTextfield = (Integer i) -> {
            updateActionList();
        };

        newAction = new NewActionGUI(actions, updateTextfield);

        setTitle("Script Creator");
        setLayout(new BorderLayout(12, 20));

        add(new ActionPanel(actionList, newAction), BorderLayout.WEST);
        add(savePanel(), BorderLayout.EAST);
        add(startPanel(), BorderLayout.PAGE_END);

        this.revalidate();
        pack();

        addActionListeners();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
    }

    private void loadContents() {
    }

    private void saveContents() {
    }

    private void updateActionList()
    {
        actionList.setText("");
        for (int i = 0; i < actions.size(); i++)
        {
            actionList.append(i + ")" + actions.get(i).toString());
        }
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
}
