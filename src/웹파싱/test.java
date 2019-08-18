package ���Ľ�;
import java.util.Arrays;
import java.util.Vector;



public class test {

	 public final static int INF = 987654321;
	    public final static int N = 29;
	    static  String[] build = {"������","������","��û����","��������","������","�����","������","2�гⱳ����","1�гⱳ����","��ֿ��̽�","���ͽ��Ǿ��","���ǽ�","3�гⱳ����","���ν�","������","�̼���","���н�","WeeŬ����","��Ȱ������","���ǽ�","������","�����","�����","ȸ�ǽ�","�μ��","��۽�","�����1","���н�"};
	    // [from_node][to_node] = distance
	    static int[][] L = new int[N][N];

	    static {
	        for(int i=0; i<N; i++)
	            Arrays.fill(L[i], INF);
	        L[0][1] = L[1][0] = 20;
	        L[0][4] = L[4][0] = 5;
	        L[0][5] = L[5][0]=10;
	        L[0][20]= L[20][0] = 8;
	        L[2][21]= L[21][2] = 3;
	        L[2][8] = L[8][2] = 10;
	        L[2][22]=L[22][2] = 10;
	        L[3][5]= L[5][3]= 10;
	        L[3][7] = L[7][3] = 3;
	        L[3][27] = L[27][3]=10;
	        L[4][19]=L[19][4]=1;
	        L[5][23]=L[23][5]=1;
	        L[6][9]= L[9][6]=3;
	        L[6][12]=L[12][6]=10;
	        L[6][11]=L[11][6]=10;
	        L[7][17]=L[17][7]=10;
	        L[7][18]=L[18][7]=10;
	        L[7][26]=L[26][7]=4;
	        L[7][8]=L[8][7]=10;
	        L[7][12]= L[12][7]=10;
	        L[8][12]=L[12][8]=10;
	        L[8][25]= L[25][8] = 5;
	        L[9][10]= L[10][9] = 2;
	        L[11][12]=L[12][11]=10;
	        L[11][13]= L[13][11]= 1;
	        L[11][14] =L[14][11] =10;
	        L[11][15]=L[15][11]=10;
	        L[15][16]= L[16][15]= 10;
	        L[19][21]= L[21][19]=1;
	        L[23][24]= L[24][23]= 1;
	        L[24][25]= L[25][24]=1;
	    }

	    // start node���� i node������ �ִܰŸ� �ӱ����
	    public static int[] dist = new int[N];
	    static {
	        Arrays.fill(dist, INF);
	    }

	    // �� ��庰�� �湮�� ���� �ִ��� ǥ��
	    public static boolean[] visited = new boolean[N];
	    static {
	        Arrays.fill(visited, false);
	    }

	    // ������� ���� ����ȣ
	    public static int[] prev = new int[N];
	    static {
	        Arrays.fill(prev, 0);
	    }

	    // ��δ�� vector
	    static Vector<Integer> steps = new Vector<Integer>();


	    public static int shortestPath(int start, int end){

	        // ����
	        if(start == end){
	            return dist[end];
	        }

	        // �湮�� ������ ���ʴ�� ��´�
	        visited[start] = true;

	        // ����� ���� �� ª�� �Ÿ� ����� �ε���
	        int min=INF;
	        for(int i=0; i<N; i++){

	            // start node�� ���� Ʈ�� Ž��
	            // ����1 : �湮�̷�x
	            // ����2 : start node�� �����
	            if(L[start][i]!=INF && visited[i]==false){

	                // �������� start node�� �����ؼ� ���� �Ÿ� �� �� �ּҰŸ��� ������Ʈ
	                if(dist[i] > dist[start] + L[start][i]){
	                    dist[i] = dist[start] + L[start][i];
	                    prev[i] = start;    // �ּҰ�θ� �����ϴ� �����
	                }


	                // ���� ��� node ����
	                // ���� : �ּҰŸ����� ���� ���
	                
	                if(min==INF||dist[min] > dist[i]){
	                    min =i;
	                }
	            }

	        }
	       
	        // �ּҰ�θ� �������� �ٽ� recursive �ϰ� �湮�Ѵ�.
	        

	        return shortestPath(min, end);
	    }

	    public static void makingPath(int start, int end, int node){
	        if(node == start){
	            steps.add(node);
	            return;
	        }

	        makingPath(start, end, prev[node]);
	        steps.add(node);

	    }
	    public static void main(String[] args) {
	        // TODO Auto-generated method stub
	        dist[0] = 0;
	        
	        shortestPath(0,13);
	        makingPath(0,13,13);
	        
	        System.out.print("������� = ");
	        for(int node : steps){
	            System.out.print(node + " ");
	        }
	        
	        System.out.println();
	        
	        System.out.print("�� ����� ���� �����Ÿ� = ");
	        for(int d : dist){
	            System.out.print(d + " ");
	        }
	        
	    }


}