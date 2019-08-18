package 웹파싱;
import java.util.Arrays;
import java.util.Vector;



public class test {

	 public final static int INF = 987654321;
	    public final static int N = 29;
	    static  String[] build = {"행정실","도서관","시청각실","본교무실","당직실","교장실","정보실","2학년교무실","1학년교무실","헤밍웨이실","셰익스피어실","음악실","3학년교무실","진로실","생물실","미술실","과학실","Wee클래스","생활지도실","보건실","문서고","도움실","기술실","회의실","인쇄실","방송실","영어실1","수학실"};
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

	    // start node부터 i node까지의 최단거리 앙기모지
	    public static int[] dist = new int[N];
	    static {
	        Arrays.fill(dist, INF);
	    }

	    // 각 노드별로 방문한 적이 있는지 표시
	    public static boolean[] visited = new boolean[N];
	    static {
	        Arrays.fill(visited, false);
	    }

	    // 각노드의 이전 노드번호
	    public static int[] prev = new int[N];
	    static {
	        Arrays.fill(prev, 0);
	    }

	    // 경로담는 vector
	    static Vector<Integer> steps = new Vector<Integer>();


	    public static int shortestPath(int start, int end){

	        // 기저
	        if(start == end){
	            return dist[end];
	        }

	        // 방문한 노드들을 차례대로 담는다
	        visited[start] = true;

	        // 연결된 노드들 중 짧은 거리 노드의 인덱스
	        int min=INF;
	        for(int i=0; i<N; i++){

	            // start node의 하위 트리 탐색
	            // 조건1 : 방문이력x
	            // 조건2 : start node와 연결됨
	            if(L[start][i]!=INF && visited[i]==false){

	                // 기존값과 start node를 경유해서 오는 거리 비교 후 최소거리로 업데이트
	                if(dist[i] > dist[start] + L[start][i]){
	                    dist[i] = dist[start] + L[start][i];
	                    prev[i] = start;    // 최소경로를 제공하는 전노드
	                }


	                // 다음 경로 node 갱신
	                // 조건 : 최소거리값이 작은 노드
	                
	                if(min==INF||dist[min] > dist[i]){
	                    min =i;
	                }
	            }

	        }
	       
	        // 최소경로를 시작으로 다시 recursive 하게 방문한다.
	        

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
	        
	        System.out.print("최종경로 = ");
	        for(int node : steps){
	            System.out.print(node + " ");
	        }
	        
	        System.out.println();
	        
	        System.out.print("각 노드의 최적 누적거리 = ");
	        for(int d : dist){
	            System.out.print(d + " ");
	        }
	        
	    }


}