import java.awt.*;

public class Przeszkody {
    private Game game;
    private Przeszkoda p1;
    private Przeszkoda p2;
    private Przeszkoda p3;
    private Przeszkoda p4;
    private Przeszkoda p5;
    private Przeszkoda p6;
    private int liczCzas;
    private int liczK;
    private Ball ball;

    public Przeszkody(Game game, Ball ball){
        this.game=game;
        this.ball=ball;
    }
    void start(){
        liczCzas=2000;
        liczK=1;
        p1 = new Przeszkoda(game,-1000);
        p2 = new Przeszkoda(game,-1000);
        p3 = new Przeszkoda(game,-1000);
        p4 = new Przeszkoda(game,-1000);
        p5 = new Przeszkoda(game,-1000);
        p6 = new Przeszkoda(game,-1000);
    }
    Przeszkoda generujPrzeszkode(Przeszkoda p){
        return new Przeszkoda(game);
    }

    void rysujPrzeszkody(Graphics2D g){
              g.drawImage(p1.image,p1.startX,p1.startY,null);
              g.drawImage(p2.image,p2.startX,p2.startY,null);
              g.drawImage(p3.image,p3.startX,p3.startY,null);
              g.drawImage(p4.image,p4.startX,p4.startY,null);
              g.drawImage(p5.image,p5.startX,p5.startY,null);
              g.drawImage(p6.image,p6.startX,p6.startY,null);
    }
    void przesuwajPrzeszkode(){
               p1.startX-=game.szybkosc;
               p2.startX-=game.szybkosc;
               p3.startX-=game.szybkosc;
               p4.startX-=game.szybkosc;
               p5.startX-=game.szybkosc;
               p6.startX-=game.szybkosc;
        if (p1.sprawdz(ball)) game.restart();
        if (p2.sprawdz(ball)) game.restart();
        if (p3.sprawdz(ball)) game.restart();
        if (p4.sprawdz(ball)) game.restart();
        if (p5.sprawdz(ball)) game.restart();
        if (p6.sprawdz(ball)) game.restart();
    }

    void resetujPrzeszkody(){
        start();
    }
    synchronized void paint(Graphics2D g){
        if (game.realTime > liczCzas) {
            liczCzas+=(int)(Math.random()*1000+750);
            if (liczK==0 )
                p1=generujPrzeszkode(p1);
            if (liczK==1)
                p2=generujPrzeszkode(p2);
            if (liczK==2 )
                p3=generujPrzeszkode(p3);
            if (liczK==3 )
                p4=generujPrzeszkode(p4);
            if (liczK==4 )
                p5=generujPrzeszkode(p5);
            if (liczK==5 )
                {p6=generujPrzeszkode(p6);liczK=-1;}
            liczK++;
        }
        rysujPrzeszkody(g);
    }
}
