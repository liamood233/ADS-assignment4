import java.util.*;

public class DijkstraScottish {
    static class Road {
        String to;
        int dist;
        Road(String to, int dist) { this.to = to; this.dist = dist; }
    }

    Map<String, List<Road>> map = new HashMap<>();

    public DijkstraScottish() {
        String[] places = {"Edinburgh", "Glasgow", "Stirling", "Perth", "Dundee"};
        for (String p : places) map.put(p, new ArrayList<>());
        // add roads both ways
        addRoad("Edinburgh", "Glasgow", 44);
        addRoad("Edinburgh", "Stirling", 36);
        addRoad("Glasgow", "Stirling", 26);
        addRoad("Stirling", "Perth", 31);
        addRoad("Perth", "Dundee", 22);
    }

    void addRoad(String a, String b, int w) {
        map.get(a).add(new Road(b, w));
        map.get(b).add(new Road(a, w));
    }

    void findPath(String start, String end) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        // priority queue with comparator (no lambda)
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            public int compare(String x, String y) {
                return dist.get(x) - dist.get(y);
            }
        });
        for (String node : map.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            prev.put(node, null);
        }
        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            String cur = pq.poll();
            for (Road r : map.get(cur)) {
                int newD = dist.get(cur) + r.dist;
                if (newD < dist.get(r.to)) {
                    dist.put(r.to, newD);
                    prev.put(r.to, cur);
                    pq.add(r.to);
                }
            }
        }

        System.out.println("Shortest distance: " + dist.get(end) + " miles");
        System.out.print("Path: ");
        Stack<String> rev = new Stack<>();
        String at = end;
        while (at != null) {
            rev.push(at);
            at = prev.get(at);
        }
        while (!rev.isEmpty()) {
            System.out.print(rev.pop());
            if (!rev.isEmpty()) System.out.print(" -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DijkstraScottish d = new DijkstraScottish();
        d.findPath("Edinburgh", "Dundee");
    }
}