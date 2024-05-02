package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resource {
    public static BufferedImage getResourceimage(String path){
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  img;
    }
}
