import java.util.*;

public class Game2048 {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);Random r=new Random();int[][] a=new int[4][4];
        add(a,r);add(a,r);
        while(true){
            for(int[] x:a){for(int v:x)System.out.printf("%5s",v==0?".":v);System.out.println();}
            System.out.print("\nWASD (q=quit): ");char c=s.next().toLowerCase().charAt(0);
            if(c=='q')break;
            int[][] old=new int[4][4];for(int i=0;i<4;i++)old[i]=a[i].clone();
            for(int z=0;z<(c=='d'?2:c=='s'?1:c=='w'?3:0);z++)rotate(a);
            for(int i=0;i<4;i++){
                int[] b=new int[4];int k=0;
                for(int v:a[i])if(v!=0)b[k++]=v;
                for(int j=0;j<3;j++)if(b[j]!=0&&b[j]==b[j+1]){b[j]*=2;for(int q=j+1;q<3;q++)b[q]=b[q+1];b[3]=0;}
                a[i]=b;
            }
            for(int z=0;z<(c=='d'?2:c=='s'?3:c=='w'?1:0);z++)rotate(a);
            if(!Arrays.deepEquals(a,old))add(a,r);
        }
    }
    static void add(int[][] a,Random r){
        ArrayList<Integer> e=new ArrayList<>();
        for(int i=0;i<16;i++)if(a[i/4][i%4]==0)e.add(i);
        if(!e.isEmpty()){int p=e.get(r.nextInt(e.size()));a[p/4][p%4]=r.nextInt(10)==0?4:2;}
    }
    static void rotate(int[][] a){
        for(int i=0;i<2;i++)for(int j=i;j<3-i;j++){
            int t=a[i][j];a[i][j]=a[3-j][i];a[3-j][i]=a[3-i][3-j];a[3-i][3-j]=a[j][3-i];a[j][3-i]=t;
        }
    }
}
