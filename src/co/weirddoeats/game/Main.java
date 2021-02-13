package co.weirddoeats.game;

public class Main {

    public static void main(String[] args)  {

        System.out.println("Yeah!");

        Game game = new Game();

        try {
            game.start();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

}
