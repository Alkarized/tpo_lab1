package task1;

public class MathArc {

    public double arcsin(final double x) {
        if (x > 1 || x < -1) {
            return Double.NaN;
        }

        double ans = x;
        double temp = 1;
        double count = 1;
        double top = 1;
        double bot = 2;
        double calc;

        while (temp > 0.001) {
            //System.out.printf("before3:: top - %s, bot - %s, count - %s\n", top, bot, count);
            calc = 2 * count + 1;
            temp = Math.pow(x, calc) * top / (bot * calc);

            //System.out.printf("after:: top - %s, bot - %s, count - %s\n", top, bot, count);

            top *= 2 * count - 1;
            bot *= 2 * count;
            ans += temp;
            count++;
        }

        return ans;
    }
}
