import java.util.*;

public class TinyRogue {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);Random r=new Random();
        int n=12,px=1,py=1,mx=9,my=9,hp=5,gold=0;
        char[][] a=new char[n][n];
        for(int x=0;x<n;x++)for(int y=0;y<n;y++)
            a[x][y]=(x==0||y==0||x==n-1||y==n-1||r.nextInt(8)==0)?'#':'.';
        a[px][py]=a[mx][my]='.';
        for(int i=0;i<7;i++){int x,y;do{x=r.nextInt(n-2)+1;y=r.nextInt(n-2)+1;}while(a[x][y]!='.');a[x][y]='$';}
        while(hp>0&&gold<5){
            System.out.println("\nHP: "+hp+"  GOLD: "+gold+"/5");
            for(int x=0;x<n;x++){for(int y=0;y<n;y++)System.out.print(x==px&&y==py?'@':x==mx&&y==my?'M':a[x][y]);System.out.println();}
            System.out.print("WASD: ");char c=s.next().toLowerCase().charAt(0);
            int dx=c=='w'?-1:c=='s'?1:0,dy=c=='a'?-1:c=='d'?1:0;
            if(a[px+dx][py+dy]!='#'){px+=dx;py+=dy;}
            if(a[px][py]=='$'){gold++;a[px][py]='.';}
            int dxm=Integer.compare(px,mx),dym=Integer.compare(py,my);
            if(r.nextBoolean()&&a[mx+dxm][my]!='#')mx+=dxm;
            else if(a[mx][my+dym]!='#')my+=dym;
            if(px==mx&&py==my){hp--;mx=9;my=9;System.out.println("The monster hit you!");}
        }
        System.out.println(hp>0?"You escaped with the gold!":"You died.");
    }
}
