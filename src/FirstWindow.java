import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame {

    private JTextField textFStep = new JTextField("");
    private JTextField textFX0 = new JTextField("");
    private JTextField textFY0 = new JTextField("");
    private JTextField textFXFinal= new JTextField("");
    private JLabel labelStep = new JLabel("Step:");
    private JLabel labelX0 = new JLabel("X0:");
    private JLabel labelY0 = new JLabel("Y0:");
    private JLabel labelXFinal = new JLabel("X(final):");
    private JLabel empty = new JLabel("");
    private JButton build = new JButton("Build");

    public FirstWindow(){

        super("FirstWindow");
        this.setBounds(100, 100, 250, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getRootPane().setDefaultButton(build);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2,2));
        container.add(labelStep);
        container.add(textFStep);
        container.add(labelX0);
        container.add(textFX0);
        container.add(labelY0);
        container.add(textFY0);
        container.add(labelXFinal);
        container.add(textFXFinal);
        container.add(empty);
        container.add(build, BorderLayout.SOUTH);

        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "OK";
                if (textFStep.getText().equals("") | textFX0.getText().equals("") | textFY0.getText().equals("") | textFXFinal.getText().equals("")){
                    message = "Please, enter all required data";
                    JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    try{
                        Float.valueOf(textFStep.getText());
                        Float.valueOf(textFX0.getText());
                        Float.valueOf(textFY0.getText());
                        Float.valueOf(textFXFinal.getText());
                    }catch (java.lang.NumberFormatException e1){
                        message = "Wrong format!";
                        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    if(message.equals("OK")){
                        Graphs graphs = new Graphs(textFStep.getText(), textFX0.getText(), textFY0.getText(), textFXFinal.getText());
                        delete();
                    }
                }
            }
        });
    }

    public void delete(){
        this.dispose();
    }
}
