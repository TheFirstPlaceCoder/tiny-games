import java.util.*;

public class Minesweeper {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in); Random r=new Random();
        int n=8,mines=10,left=n*n-mines; boolean[][] m=new boolean[n][n],o=new boolean[n][n];
        for(int i=0;i<mines;){int x=r.nextInt(n),y=r.nextInt(n);if(!m[x][y]){m[x][y]=true;i++;}}
        while(left>0){
            System.out.println("\n  0 1 2 3 4 5 6 7");
            for(int x=0;x<n;x++){
                System.out.print(x+" ");
                for(int y=0;y<n;y++)System.out.print((o[x][y]?""+count(m,x,y):"#")+" ");
                System.out.println();
            }
            System.out.print("Row column: ");
            int x=s.nextInt(),y=s.nextInt();
            if(x<0||y<0||x>=n||y>=n||o[x][y])continue;
            if(m[x][y]){
                System.out.println("BOOM! You lost.");
                return;
            }
            o[x][y]=true;left--;
        }
        System.out.println("You win!");
    }
    static char count(boolean[][] m,int x,int y){
        int c=0;
        for(int i=-1;i<=1;i++)for(int j=-1;j<=1;j++)
            if(x+i>=0&&y+j>=0&&x+i<m.length&&y+j<m.length&&m[x+i][y+j])c++;
        return (char)('0'+c);
    }
}
