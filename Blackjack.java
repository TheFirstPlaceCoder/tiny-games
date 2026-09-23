import java.util.*;

public class Blackjack {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in); Random r=new Random();
        int p=0,d=0,a=0,b=0;
        while(p<21){
            int c=r.nextInt(13)+1,c2=Math.min(c,10);
            if(c==1){c2=11;a++;}
            p+=c2;
            while(p>21&&a>0){p-=10;a--;}
            System.out.println("You: "+p);
            if(p>=21)break;
            System.out.print("[h]it / [s]tand: ");
            if(s.next().equalsIgnoreCase("s"))break;
        }
        if(p>21){System.out.println("Bust! Dealer wins.");return;}
        while(d<17){
            int c=r.nextInt(13)+1,c2=Math.min(c,10);
            if(c==1){c2=11;b++;}
            d+=c2;
            while(d>21&&b>0){d-=10;b--;}
        }
        System.out.println("Dealer: "+d);
        System.out.println(d>21||p>d?"You win!":p==d?"Draw!":"Dealer wins!");
    }
}
