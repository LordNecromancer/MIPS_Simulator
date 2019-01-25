public class Instruction {
    private String type = "";
    private String a = "0";
    private String b = "0";
    private String c = "0";
    String binary = "";

    public Instruction(String type, String a, String b, String c) {
        this.type = type;
        this.a = a;
        this.b = b;
        this.c = c;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    String getBits(int i, int j) {

        if (!binary.equals(""))
            if (j == 32) {
                return this.binary.substring(i);
            } else {
                return this.binary.substring(i, j);
            }
        return "0";
    }
}
