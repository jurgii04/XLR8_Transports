package Windows;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import static Windows.login.ea;

public class FotoReader extends JFrame {
    private String out;

    public FotoReader() {
        // create a label to display the image

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(file);
                // save the image to a file
                File outputFile = new File("src\\Windows\\images\\"+ea+".png");
                ImageIO.write((BufferedImage) image, "png", outputFile);
                // return the file path of the output file
                String outputFilePath = outputFile.getAbsolutePath();
                dispose();  // close the frame after the file path is returned
                out= outputFilePath;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }



    }
    public String getOut() {
        return out;
    }
    public void copy_image(String path){



        Path source = Paths.get(getOut());
        Path destination = Paths.get(path);

        try {
            Files.copy(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Image file copied successfully.");
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\ik012982i9\\IdeaProjects\\XLR8_TransportsNew\\src\\Windows\\images\\PerfilFotos\\zubiri@.com.png");
        String basePath = new File("").getAbsolutePath();
        String relativePath = file.getPath().replace(basePath, "");

        System.out.println(relativePath.substring(1,relativePath.length()));
    }
}

