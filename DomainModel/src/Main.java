public class Main {
    public static void main(String[] args) {

        Robot marvin = new Robot("Marvin", 2);
        marvin.watch(new ANamed("She") {
            @Override
            public String getName() {
                return super.getName();
            }
        }, State.COLD_CONTEMPT);
        marvin.useSchemas();
    }
}
