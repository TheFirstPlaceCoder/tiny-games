import java.util.*;

public class TinyTicTacToe {
    public static void main(String[] a) {
        Scanner s=new Scanner(System.in);
        char[] b="123456789".toCharArray(); int t=0;
        for(;;){
            System.out.printf("\n%c|%c|%c\n-+-+-\n%c|%c|%c\n-+-+-\n%c|%c|%c\n",
                    b[0],b[1],b[2],b[3],b[4],b[5],b[6],b[7],b[8]);
            char p=t%2==0?'X':'O';
            System.out.print("\n"+p+"'s move: ");
            int m=s.nextInt()-1;
            if(m<0||m>8||b[m]=='X'||b[m]=='O') continue;
            b[m]=p;t++;
            String q=new String(b);
            if(q.matches("(...)*XXX.*|X..X..X..|.X..X..X.|..X..X..X|X...X...X|..X.X.X..|(...)*OOO.*|O..O..O..|.O..O..O.|..O..O..O|O...O...O|..O.O.O..")){
                System.out.println("\n"+p+" wins!");break;
            }
            if(t==9){System.out.println("\nDraw!");break;}
        }
    }
}
