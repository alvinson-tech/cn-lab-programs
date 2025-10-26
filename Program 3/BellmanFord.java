import java.util.Scanner;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int INF = 999;

        System.out.println("Enter the number of vertices: ");
        int n = sc.nextInt();
        
        int[][] graph = new int[n+1][n+1];
        System.out.println("Enter the adjency matrix: ");
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                graph[i][j] = sc.nextInt();
                if(i == j) graph[i][j] = 0;
                else if(graph[i][j] == 0) graph[i][j] = INF;
            }
        }

        System.out.println("Enter the source vertex: ");
        int src = sc.nextInt();

        int[] dist = new int[n+1];

        for(int i = 1; i <= n; i++) dist[i] = INF;
        dist[src] = 0;

        for(int k = 1; k < n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(graph[i][j] != INF && dist[i] + graph[i][j] < dist[j]){
                        dist[j] = dist[i] + graph[i][j];
                    }
                }
            }
        }

        boolean hasNegCycle = false;
        for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(graph[i][j] != INF && dist[i] + graph[i][j] < dist[j]){
                        hasNegCycle = true;
                        break;
                    }
                }
                if(hasNegCycle) break;
            }
        
        if(hasNegCycle){
            System.out.println("The Graph has Negetive Cycle");
        }
        else{
            for(int i = 1; i <= n; i++)
                System.out.println("Distance from source " + src + " to " + i + " is: " + dist[i]);
        }

        sc.close();
    }
}

