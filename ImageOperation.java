import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) (data[i] ^ key);  // XOR operation with key
                }

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
                fis.close();

                JOptionPane.showMessageDialog(null, "Operation completed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Image Encryption/Decryption");
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font font = new Font("Roboto", Font.BOLD, 18);

        // Creating panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(0xF5F5F5));

        // Label for instructions
        JLabel label = new JLabel("Enter a key for XOR operation:");
        label.setFont(font);
        label.setForeground(Color.DARK_GRAY);
        panel.add(label);

        // Text field for key input
        JTextField textField = new JTextField(10);
        textField.setFont(font);
        panel.add(textField);

        // Creating button
        JButton button = new JButton("Choose Image & Apply Key");
        button.setFont(font);
        button.setBackground(new Color(0x007BFF));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Button action
        button.addActionListener(e -> {
            try {
                String text = textField.getText();
                int key = Integer.parseInt(text);
                operate(key);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the key.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(button);

        // Adding panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
