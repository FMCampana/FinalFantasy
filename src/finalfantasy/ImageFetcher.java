package finalfantasy;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageFetcher {
    private ImageFetcher(){}
    public static ImageIcon fetchIcon(String imageFileName, String name){
      return new ImageIcon(find(imageFileName), name);
    }
    public static ImageIcon fetchIcon(String imageFileName){
      return new ImageIcon(find(imageFileName));
    }
    public static Image fetchImage(String imageFileName){
      return fetchIcon(imageFileName).getImage();
    }
    public static URL find(String imageFileName){
      URL imageResource = ImageFetcher.class.getClassLoader().getResource("images/"+imageFileName);
      return imageResource;
    }
}