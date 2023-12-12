import org.junit.Assert;
import org.junit.Test;

public class DFSTest {

    @Test
    public void searchTestTrue() throws Exception {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        boolean test = g.DFS(0, 3);
        Assert.assertTrue(test);
    }

    @Test
    public void searchTestFalse() throws Exception {
        Graph g = new Graph(4);

        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(3, 0);
        g.addEdge(3, 1);

        boolean test = g.DFS(0, 4);
        Assert.assertFalse(test);
    }
}

