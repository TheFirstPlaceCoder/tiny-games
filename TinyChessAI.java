import java.util.*;

public class TinyChessAI {
    static Random r=new Random();
    static char[][] b={
            "rnbqkbnr".toCharArray(),"pppppppp".toCharArray(),
            "........".toCharArray(),"........".toCharArray(),
            "........".toCharArray(),"........".toCharArray(),
            "PPPPPPPP".toCharArray(),"RNBQKBNR".toCharArray()
    };

    public static void main(String[] z){
        Scanner s=new Scanner(System.in);
        System.out.print("AI level 1-3: ");int lvl=s.nextInt();
        while(true){
            show();
            System.out.print("Your move: ");String a=s.next(),c=s.next();
            int y=a.charAt(0)-97,x=8-a.charAt(1)+48,Y=c.charAt(0)-97,X=8-c.charAt(1)+48;
            if(!move(b,x,y,X,Y,true))continue;
            if(!king('k')){System.out.println("You win!");break;}

            int[] m=ai(lvl);move(b,m[0],m[1],m[2],m[3],false);
            System.out.println("AI: "+(char)(m[1]+97)+(8-m[0])+" "+(char)(m[3]+97)+(8-m[2]));
            if(!king('K')){show();System.out.println("AI wins!");break;}
        }
    }

    static int[] ai(int l){
        ArrayList<int[]> m=moves(false);
        if(l==1)return m.get(r.nextInt(m.size()));
        int[] best=m.get(0);int bs=-99999;
        for(int[] x:m){
            char[][] n=copy(b);char t=n[x[2]][x[3]];move(n,x[0],x[1],x[2],x[3],false);
            int v=l==2?val(t):score(n,1,true);
            if(v>bs){bs=v;best=x;}
        }
        return best;
    }

    static int score(char[][] a,int d,boolean white){
        if(d==0)return eval(a);
        ArrayList<int[]> ms=moves(a,white);if(ms.isEmpty())return eval(a);
        int best=white?-99999:99999;
        for(int[] m:ms){
            char[][] n=copy(a);move(n,m[0],m[1],m[2],m[3],white);
            int v=score(n,d-1,!white);
            best=white?Math.max(best,v):Math.min(best,v);
        }
        return best;
    }

    static int eval(char[][] a){
        int v=0;
        for(char[] x:a)for(char p:x)v+=(Character.isUpperCase(p)?-1:1)*val(p);
        return v;
    }

    static int val(char p){
        return switch(Character.toLowerCase(p)){case'p'->1;case'n','b'->3;case'r'->5;case'q'->9;case'k'->100;default->0;};
    }

    static ArrayList<int[]> moves(boolean w){return moves(b,w);}
    static ArrayList<int[]> moves(char[][] a,boolean w){
        ArrayList<int[]> m=new ArrayList<>();
        for(int x=0;x<8;x++)for(int y=0;y<8;y++)
            if(a[x][y]!='.'&&Character.isUpperCase(a[x][y])==w)
                for(int X=0;X<8;X++)for(int Y=0;Y<8;Y++)
                    if(ok(a,x,y,X,Y,w))m.add(new int[]{x,y,X,Y});
        return m;
    }

    static boolean move(char[][] a,int x,int y,int X,int Y,boolean w){
        if(!ok(a,x,y,X,Y,w))return false;
        char p=a[x][y];a[X][Y]=p;a[x][y]='.';
        if(Character.toLowerCase(p)=='p'&&(X==0||X==7))a[X][Y]=w?'Q':'q';
        return true;
    }

    static boolean ok(char[][] a,int x,int y,int X,int Y,boolean w){
        if(x<0||x>7||y<0||y>7||X<0||X>7||Y<0||Y>7)return false;
        char p=a[x][y],t=a[X][Y];
        if(p=='.'||Character.isUpperCase(p)!=w||t!='.'&&Character.isUpperCase(t)==w)return false;
        int dx=X-x,dy=Y-y,ax=Math.abs(dx),ay=Math.abs(dy);
        return switch(Character.toLowerCase(p)){
            case'p'->dy==0&&dx==(w?-1:1)&&t=='.'||dy==0&&x==(w?6:1)&&dx==(w?-2:2)&&t=='.'&&a[x+(w?-1:1)][y]=='.'||ay==1&&dx==(w?-1:1)&&t!='.';
            case'n'->ax*ay==2;
            case'b'->ax==ay&&clear(a,x,y,X,Y);
            case'r'->(dx==0||dy==0)&&clear(a,x,y,X,Y);
            case'q'->(dx==0||dy==0||ax==ay)&&clear(a,x,y,X,Y);
            case'k'->ax<=1&&ay<=1;
            default->false;
        };
    }

    static boolean clear(char[][] a,int x,int y,int X,int Y){
        int dx=Integer.compare(X,x),dy=Integer.compare(Y,y);
        for(x+=dx,y+=dy;x!=X||y!=Y;x+=dx,y+=dy)if(a[x][y]!='.')return false;
        return true;
    }

    static boolean king(char k){
        for(char[] x:b)for(char p:x)if(p==k)return true;
        return false;
    }

    static char[][] copy(char[][] a){
        char[][] n=new char[8][];
        for(int i=0;i<8;i++)n[i]=a[i].clone();
        return n;
    }

    static void show(){
        System.out.println("\n  a b c d e f g h");
        for(int i=0;i<8;i++){System.out.print((8-i)+" ");for(char c:b[i])System.out.print(c+" ");System.out.println();}
    }
}
