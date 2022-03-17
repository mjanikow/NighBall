import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plansza {

    private Game game;
    private int x;
    private int y;
    private int h;
    private BufferedImage Ground;
    private BufferedImage Background;
    private BufferedImage Stars;
    private int groundX;
    private int groundY;
    private int starsX;
    int starsSPEED;
    private int starsY;

    public Plansza(Game game) {
        File imageFile = new File("Ground.png");
        try {
            Ground = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File imageFile2 = new File("Background.png");
        try {
            Background = ImageIO.read(imageFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File imageFile3 = new File("Stars.png");
        try {
            Stars = ImageIO.read(imageFile3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.game = game;
    }
    int getGround(){
        return h+30;
    }
    private int dlugosc;
    void start() {
        h = game.getWidth() / 3;
        y = game.getHeight();
        x = game.getWidth();
        groundX=0;
        groundY=h+30;
        starsX=0;
        starsY=0;
        starsSPEED=1;
    }
    void moveGround(){
        if (groundX>-x*2)
            groundX-=game.szybkosc;
        else
            groundX=15;
    }
    void moveStars(){
        if (starsX>-x*2)
            starsX-=starsSPEED;
        else
            starsX=15;
    }
    void paint(Graphics2D g) {
        g.setColor(new Color(118, 112, 74));
        g.drawLine(0, h + 80, game.getWidth(), h + 80);
        g.drawImage(Background,0, 0, null);
        g.drawImage(Ground,groundX,groundY , null);
        g.drawImage(Stars,starsX,starsY , null);

    }
}