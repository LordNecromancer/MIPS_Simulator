public class Register {
    private int data;
    private int num;
    private String name;

    public Register(int data, int num, String name) {
        this.data = data;
        this.num = num;
        this.name = name;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
