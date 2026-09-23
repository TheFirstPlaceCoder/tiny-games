import java.util.*;

public class Snake {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);Random r=new Random();
        LinkedList<Integer> snake=new LinkedList<>();snake.add(55);
        int food=r.nextInt(100),dir=1;
        while(true){
            for(int i=0;i<100;i++){
                if(i%10==0)System.out.println();
                System.out.print(i==food?"* ":snake.contains(i)?"O ":". ");
            }
            System.out.print("\nWASD: ");char c=s.next().toLowerCase().charAt(0);
            dir=c=='w'?-10:c=='s'?10:c=='a'?-1:c=='d'?1:dir;
            int h=snake.getFirst(),n=h+dir;
            if(n<0||n>=100||(dir==1&&h%10==9)||(dir==-1&&h%10==0)||snake.contains(n)){
                System.out.println("Game over! Score: "+(snake.size()-1));break;
            }
            snake.addFirst(n);
            if(n==food){
                do food=r.nextInt(100);while(snake.contains(food));
            }else snake.removeLast();
        }
    }
}
