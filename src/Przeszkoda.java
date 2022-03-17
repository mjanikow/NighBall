
import java.awt.image.BufferedImage;


public class Przeszkoda {
    String name;
    BufferedImage image;
    private int ground;
    int startX;
    int startY;
    int test;
    Przeszkoda(Game game){
        ground=720;
        startX=2000;
        image = game.vecImages.get((int)(Math.random()*game.vecImages.size()));
        startY=ground-image.getHeight();
    }
    Przeszkoda(Game game, int X){
        ground=720;
        startX=X;
        image = game.vecImages.get((int)(Math.random()*game.vecImages.size()));
        startY=ground-image.getHeight();
    }
    private int[] colX(){
        return new int[]{startX,startX+image.getWidth()};
    }
    private int[] colY(){
        return new int[]{startY,startY+image.getHeight()};
    }

    private boolean zakresY(int ky2, int py1){
        return ky2>py1;
    }
    private boolean zakresX(int kx1, int kx2, int px1, int px2){
        return (kx2>px1 && kx2<px2) || kx1<px2 && kx1 > px1;
    }
    private boolean zakresX2(int kx1, int px1, int px2){
        int srodek = kx1+42;
        return srodek>px1 && srodek <px2;
    }

    boolean sprawdz(Ball ball){
        return (zakresX(ball.colX()[0],ball.colX()[1],colX()[0],colX()[1]) || zakresX2(ball.colX()[0],colX()[0],colX()[1]))
                && zakresY(ball.colY()[1],colY()[0]);
    }

}
