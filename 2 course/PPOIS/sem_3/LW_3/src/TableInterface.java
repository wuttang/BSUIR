public interface TableInterface {
    void occupy() throws Exception;
    void vacate();
    String getStatus();
}