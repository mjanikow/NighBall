import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Images {
    List<BufferedImage> images = new ArrayList<BufferedImage>();
    private static final File dir = new File("Przeszkody");
    private static final String[] EXTENSIONS = new String[]{"png"};
    private static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    public Images() {
        if (dir.isDirectory()) {
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                try {
                    images.add(ImageIO.read(f));
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

