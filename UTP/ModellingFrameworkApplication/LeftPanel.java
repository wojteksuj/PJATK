import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class LeftPanel extends JPanel {

    private RightPanel rightPanel;

    public LeftPanel(RightPanel rightPanel) {
        this.rightPanel = rightPanel;

        this.setLayout(new BorderLayout());
        JPanel listPanel = new JPanel(new GridLayout(1, 2));

        String[] models = {"Model1", "Model2", "Model3"};
        JList<String> modelList = new JList<>(models);
        JScrollPane modelScrollPanel = new JScrollPane(modelList);
        listPanel.add(modelScrollPanel);

        String[] dataFiles = {"Data1.txt", "Data2.txt", "Data3.txt"};
        JList<String> dataList = new JList<>(dataFiles);
        JScrollPane dataScrollPanel = new JScrollPane(dataList);
        listPanel.add(dataScrollPanel);
        this.add(listPanel, BorderLayout.CENTER);


        JButton runButton = new JButton("Run model");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedModel = modelList.getSelectedValue();
                String selectedData = dataList.getSelectedValue();

                Controller ctl = new Controller(selectedModel);
                ctl.readDataFrom(selectedData).runModel();
                String result = ctl.getResultsAsTsv();
                rightPanel.appendToTextArea(result);

            }
        });

        JButton runScriptButton = new JButton("Run script from file");
        runScriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedModel = modelList.getSelectedValue();
                String selectedData = dataList.getSelectedValue();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int path = fileChooser.showOpenDialog(LeftPanel.this);

                if (path == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String scriptPath = selectedFile.getAbsolutePath();
                    Controller ctl = new Controller(selectedModel);
                    ctl.readDataFrom(selectedData).runModel().runScriptFromFile(scriptPath);
                    String result = ctl.getResultsAsTsv();
                    rightPanel.appendToTextArea(result);

                } else {
                    System.out.println("No script file found!");
                }


            }
        });

        JButton createScriptButton = new JButton("Create and run script");
        createScriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedModel = modelList.getSelectedValue();
                String selectedData = dataList.getSelectedValue();

                openScriptEditor(selectedModel, selectedData);


            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(runButton);
        buttonPanel.add(runScriptButton);
        buttonPanel.add(createScriptButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openScriptEditor(String model, String data) {

        JFrame scriptEditor = new JFrame("Script");
        scriptEditor.setSize(400, 300);
        scriptEditor.setLayout(new BorderLayout());


        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scriptEditor.add(scrollPane, BorderLayout.CENTER);


        JButton runScript = new JButton("Run script");
        runScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = textArea.getText();

                Controller ctl = new Controller(model);
                ctl.readDataFrom(data).runModel().runScript(text);
                String result = ctl.getResultsAsTsv();
                rightPanel.appendToTextArea(result);
            }
        });

        scriptEditor.add(runScript, BorderLayout.SOUTH);

        scriptEditor.setVisible(true);
    }


}
