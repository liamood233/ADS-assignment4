import java.util.*;
public class GraphTraversal {
    Map<String, List<String>> g;
    public GraphTraversal() {
        g = new HashMap<>();
        // from assignment
        g.put("A", Arrays.asList("C", "B", "D"));
        g.put("B", Arrays.asList("A", "C", "E", "G"));
        g.put("C", Arrays.asList("A", "B", "D"));
        g.put("D", Arrays.asList("C", "A"));
        g.put("E", Arrays.asList("G", "F", "B"));
        g.put("F", Arrays.asList("G", "E"));
        g.put("G", Arrays.asList("F", "B"));
    }
    // DFS recursive
    void dfs(String v, Set<String> seen, List<String> out) {
        seen.add(v);
        out.add(v);
        for (String nb : g.get(v)) {
            if (!seen.contains(nb)) {
                dfs(nb, seen, out);
            }
        }
    }
    List<String> runDFS(String start) {
        Set<String> seen = new HashSet<>();
        List<String> out = new ArrayList<>();
        dfs(start, seen, out);
        return out;
    }
    // BFS using queue
    List<String> runBFS(String start) {
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        List<String> out = new ArrayList<>();
        q.add(start);
        seen.add(start);
        while (!q.isEmpty()) {
            String v = q.poll();
            out.add(v);
            for (String nb : g.get(v)) {
                if (!seen.contains(nb)) {
                    seen.add(nb);
                    q.add(nb);
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        GraphTraversal gt = new GraphTraversal();
        System.out.println("DFS: " + gt.runDFS("A"));
        System.out.println("BFS: " + gt.runBFS("A"));
    }
}