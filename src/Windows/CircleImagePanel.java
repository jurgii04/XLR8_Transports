package Windows;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CircleImagePanel extends JPanel  {

    private BufferedImage image ,orgimg;

    public CircleImagePanel(File file) throws IOException {
        // read the image file
        orgimg = ImageIO.read(file);

        // create a new BufferedImage with the desired size (150x150)
        BufferedImage resizedImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);

        // Create Graphics2D object for the resized image
        Graphics2D g2d = resizedImage.createGraphics();

        // Set rendering hints for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a circular mask image
        BufferedImage maskImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D maskGraphics = maskImage.createGraphics();

        // Set the background of the mask to transparent
        maskGraphics.setColor(new Color(0, 0, 0, 0));
        maskGraphics.fillRect(0, 0, 150, 150);

        // Set the fill color to white
        maskGraphics.setColor(Color.WHITE);

        // Draw a filled circle on the mask
        int diameter = Math.min(150, 150);
        int xOffset = (150 - diameter) / 2;
        int yOffset = (150 - diameter) / 2;
        maskGraphics.fillOval(xOffset, yOffset, diameter, diameter);

        // Apply the mask to the resized image
        g2d.drawImage(orgimg.getScaledInstance(150, 150, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0f));
        g2d.drawImage(maskImage, 0, 0, null);

        // Dispose the graphics objects
        g2d.dispose();
        maskGraphics.dispose();

        // Set the modified image as the image for drawing
        image = resizedImage;

        // Set the panel size to match the image size
        setPreferredSize(new Dimension(150, 150));
    }








    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // get the panel size
        int width = getWidth();
        int height = getHeight();

        // calculate the radius of the circle
        int radius = Math.min(width, height) / 2;

        // calculate the center point of the panel
        int centerX = width / 2;
        int centerY = height / 2;

        // create a new BufferedImage with the same size as the panel
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // set the rendering hints to smooth the edges of the circle
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // draw the circle
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // set the clipping shape to the circle
        g2d.setClip(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // draw the image inside the circle
        int x = (width - image.getWidth()) / 2;
        int y = (height - image.getHeight()) / 2;
        g2d.drawImage(image, x, y, null);

        // dispose the graphics object
        g2d.dispose();


        // draw the final image to the panel
        g.drawImage(bufferedImage, 0, 0, null);
    }
    public static void main(String[] args) {
        // create a file object for the image file
        File imageFile = new File("src\\Windows\\images\\PerfilFotos\\jurgi.png");

        // create a new Perfil window
        JFrame frame = new JFrame();

        // create a new panel with the circle image
        try {
            CircleImagePanel imagePanel = new CircleImagePanel(imageFile);

            // add the panel to the window
            frame.add(imagePanel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // resize the window to fit the panel
        frame.pack();

        // center the window on the screen
        frame.setLocationRelativeTo(null);

        // show the window
        frame.setVisible(true);
    }



}
