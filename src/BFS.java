import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS
{
    public static void main(String [] args) throws Exception
    {
        File file = new File("maze.in");

        Scanner sc = new Scanner(file);
        int height=0;
        int width=0;

        while(sc.hasNextLine())
        {
            height++;
            String s =sc.nextLine();
            width=s.length();
        }
        file = new File("maze.in");
        sc = new Scanner(file);

        vertex[][] board = new vertex[height][width];
        vertex start = null;
        height = 0;

        while(sc.hasNextLine())
        {
            String l = sc.nextLine();
            //        System.out.println(l);

            for(int x=0;x<l.length();x++)
            {
                board[height][x]=new vertex(height,x,l.charAt(x));
                if(board[height][x].getVal()=='S')
                    start = board[height][x];
            }
            height++;
        }
        for(int x=0;x<board.length;x++)
        {
            for(int y=0;y<board[0].length;y++)
            {
                System.out.print(board[x][y].getVal());
            }
            System.out.println();
        }

        vertex bfs = BFS(board,start);

        if(bfs!=null)
            System.out.println(bfs.getRow()+" "+bfs.getCol()+" "+bfs.getLevel()+" "+bfs.getVal());
        else
            System.out.println("Not Found");
    }
    public static vertex BFS(vertex[][] board, vertex start)
    {
        start.setLevel(0);


        LinkedList<vertex> ll = new LinkedList<>();
        ll.add(start);
        LinkedList<vertex> newll = new LinkedList<>();

        while(ll.isEmpty()==false)
        {
            vertex parent = ll.removeFirst();
            LinkedList<vertex> vertexes = parent.getNeighbors(board);

            while(vertexes.isEmpty()==false)
            {
                vertex v = vertexes.removeFirst();

                if(v.getVal()=='F')
                {
                    v.setLevel(parent.getLevel()+1);
                    v.setParent(parent);
                    return v;
                }
                if(v.level==-1 && v.getVal()=='*')
                {
                    ll.addLast(v);
                    v.setLevel(parent.getLevel()+1);
                    v.setParent(parent);
                }
            }
        }

        return null;
    }
    public static class vertex
    {
        private int row;
        private int col;
        private int level;
        private vertex parent;
        private char val;


        public vertex(int row, int col, char val)
        {
            this.row=row;
            this.col=col;
            this.val=val;
            level=-1;
            parent=null;
        }

        public char getVal()
        {
            return val;
        }

        public void setVal(char val)
        {
            this.val = val;
        }

        public int getRow()
        {
            return row;
        }

        public void setRow(int row)
        {
            this.row = row;
        }

        public int getCol()
        {
            return col;
        }

        public void setCol(int col)
        {
            this.col = col;
        }

        public int getLevel()
        {
            return level;
        }

        public void setLevel(int level)
        {
            this.level = level;
        }

        public vertex getParent()
        {
            return parent;
        }

        public void setParent(vertex parent)
        {
            this.parent = parent;
        }
        public LinkedList getNeighbors(vertex[][] board)
        {
            LinkedList<vertex> ll = new LinkedList<>();
            if(row+1<board[0].length)
                ll.add(board[row+1][col]);
            if(row-1>=0)
                ll.add(board[row-1][col]);
            if(col+1<board.length)
                ll.add(board[row][col+1]);
            if(col-1>=0)
                ll.add(board[row][col-1]);
            return ll;
        }
    }
}
