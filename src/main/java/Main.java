public class Main {
    public static void main(String[] args) throws Exception {
        new ApplicationServer(8282, new MyApplication()).start();
    }
}