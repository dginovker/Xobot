package GUI;

import Actions.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by SRH on 1/9/2018.
 */
public class NewActionGUI extends JFrame {

    private final String[] actionTypes = {"Interact with", "Use item on", "Type", "Click (x,y)"};

    private JTextArea first = new JTextArea(), second = new JTextArea(), third = new JTextArea();
    private JLabel firstDesc = new JLabel(), secondDesc = new JLabel(), thirdDesc = new JLabel();

    private JButton add = new JButton("Add");
    private String selectedAction;

    private ArrayList<Statement> actionList;

    public NewActionGUI(ArrayList<Statement> actionList) {
        this.actionList = actionList;
        setTitle("Add new action");
        setLayout(new BorderLayout());

        JPanel dropDownAndDesc = new JPanel(new GridLayout(2, 1));
        dropDownAndDesc.add(actionTypeCombo());
        dropDownAndDesc.add(new JLabel("To find the IDs of entities/etc, click the \"Debug\" dropdown on the top right corner of the client."));
        add(dropDownAndDesc, BorderLayout.PAGE_START);

        add(fillInfo());

        add(add, BorderLayout.PAGE_END);

        setSize(650, 300);

        add.addActionListener(o -> {
            actionList.add(new Actions.Statement(selectedAction, first.getText(), second.getText(), third.getText()));
            this.setVisible(false);
        });
    }

    private JPanel fillInfo() {
        JPanel fillInfo = new JPanel();
        fillInfo.setLayout(new GridLayout(3, 1, 20, 20));

        fillInfo.add(detailGrabber(first, firstDesc));
        fillInfo.add(detailGrabber(second, secondDesc));
        fillInfo.add(detailGrabber(third, thirdDesc));

        return fillInfo;
    }

    private JPanel detailGrabber(JTextArea area, JLabel lab) {
        JPanel forum = new JPanel();
        forum.setLayout(new FlowLayout(FlowLayout.LEFT));
        forum.add(area);
        forum.add(lab);
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        area.setColumns(8);

        return forum;
    }

    private JComboBox actionTypeCombo() {
        JComboBox actionType = new JComboBox(actionTypes);
        actionType.addActionListener(o -> {
            selectedAction = actionType.getSelectedItem().toString();
            switch (actionType.getSelectedIndex())
            {
                case 0:
                    setDesc("Entity to interact with (eg. 4296 = banker)", "Option to select (eg. \"Bank\")(cAsE sEnSiTivE)");
                    break;
                case 1:
                    setDesc("Item in your inventory to use (eg. 999 = \"Bones\")", "Entity to use it on (eg. 999 = altar)<999 isn't actually altar>", "Item option to select (can be left blank)(eg. 1 = \"Bury\")");
                    break;
                case 2:
                    setDesc("Text to type in (eg. 28)", "Hit enter? (0 for no, 1 for yes)");
                    break;
                case 3:
                    setDesc("X coordinate to click (eg. 0)", "Y coordinate to click (eg. 600)", "Right click? (0 for no, 1 for yes)");
            }
        });

        return actionType;
    }

    private void setDesc(String s, String s1) {
        setDesc(s, s1, "");
    }
    private void setDesc(String s, String s1, String s2)
    {
        firstDesc.setText(s);
        secondDesc.setText(s1);
        thirdDesc.setText(s2);
    }
}
