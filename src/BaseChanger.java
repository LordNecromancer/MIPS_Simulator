public class BaseChanger {

    public String changeToBaseTwo(int a) {
        String result = "";
        while (a >= 2) {
            result = "" + (a % 2) + result;
            a = a / 2;
        }
        if (a == 1) {
            result = a + result;
        }
        return result;
    }

    public int changeToBaseTen(String a) {
        int result = 0;
        if (a != null) {
            for (int i = 0; i < a.length(); i++) {
                result = ((Integer.parseInt(a.charAt(a.length() - i - 1) + "")) * (int) Math.pow(2, (double) i)) + result;
            }
        }
        return result;
    }

    public String Xbit(String a, int digits) {
        while (a.length() < digits) {
            a = "0" + a;
        }
        return a;
    }
}
