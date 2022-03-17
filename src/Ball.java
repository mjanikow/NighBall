import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ball {

    private Game game;
    private Color kolor;
    int szybkoscObrotu;
     private int x;
      private int y;
     private int limit;
     int czySkok=0;
    Ball(Game game) {
        this.game= game;
    }

    void start() {
        kolor=Color.red;
        x = game.getHeight()/2;
        y = game.getWidth()/3;
        szybkoscObrotu=4;
    }
    boolean czyDotyka(){
        return y + 80 == 714;
    }
    int kat=0;
    private double angle = (Math.toRadians(kat));;
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }

    int[] colX(){
        return new int[]{x,x+84};
    }
    int[] colY(){
        return new int[]{y,y+84};
    }

    void rotate(){
        if(kat<360)
            kat+=szybkoscObrotu;
        else
            kat=0;
        angle = (Math.toRadians(kat));
    }
    void jump(int moc){
        limit=moc;

        Thread run = new Thread(() -> {
            for (int i=0;i<limit;i++)
            {
                if (i<limit/2 && !game.gameOver)
                    y-=1;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=y+80; y+80<714; i++){
                try {
                    if (!game.gameOver)
                    y++;
                        Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!game.gameOver)
                y=634;
        });
        run.start();
    }

    void paint(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.setColor(kolor);
        g.fillOval(x-2, y-2, 84, 84);
        g.setColor(Color.black);
        g.fillOval(x, y, 80, 80);
        g.setColor(Color.red);
        g.rotate(angle,x+40,y+40);
        g.drawLine(x,y+40,x+79,y+40);
        g.drawLine(x+40,y,x+40,y+79);
        g.setTransform(old);
    }
}