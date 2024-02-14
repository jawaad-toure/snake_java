package Snake;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SnakeImages {
    public BufferedImage appleImage;
    public BufferedImage headImage;
    public BufferedImage bodyImage;

    public SnakeImages() {
        try {
            appleImage = ImageIO.read(new File("src/apple.png"));
            headImage = ImageIO.read(new File("src/head.png"));
            bodyImage = ImageIO.read(new File("src/body.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}