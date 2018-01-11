package GUI.MainPanels;

import GUI.NewGuis.NewActionGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SRH on 1/10/2018.
 */
public class ActionPanel extends JPanel {
    private JButton actionButton = new JButton("Add Action"), startIfButton = new JButton("Begin If-Action"), endIfButton = new JButton("End If-Block"), removeButton = new JButton ("Remove Action");
    private JTextArea actionList;
    private NewActionGUI newAction;

    public ActionPanel(JTextArea actionList, NewActionGUI newAction)
    {
        this.actionList = actionList;
        this.newAction = newAction;

        setLayout(new BorderLayout(20, 0));

        this.actionList.setEditable(false);

        JScrollPane scroll = new JScrollPane(this.actionList);
        this.actionList.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
        add(scroll, BorderLayout.WEST);

        JPanel actionListButtons = new JPanel();
        actionListButtons.setLayout(new GridLayout(5, 1, 0, 20));
        actionListButtons.add(actionButton);
        actionListButtons.add(startIfButton);
        actionListButtons.add(endIfButton);
        actionListButtons.add(removeButton);

        add(actionListButtons);

        initButtons();
    }

    private void initButtons() {
        actionButton.addActionListener(o -> {
            newAction.setVisible(true);
        });
    }
}
