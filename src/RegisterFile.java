public class RegisterFile {
    Register[] registers = new Register[32];
    private boolean regWrite = false;
    private static BaseChanger baseChanger = new BaseChanger();

    public RegisterFile() {
//                $zero register :
        registers[0] = new Register(0, 0, "$zero");
        registers[1] = new Register(0, 1, "");
//                $v0-$v1 registers :
        registers[2] = new Register(0, 2, "$v0");
        registers[3] = new Register(0, 3, "$v1");

//                $a0-$a3 registers :
        registers[4] = new Register(0, 4, "$a0");
        registers[5] = new Register(0, 5, "$a1");
        registers[6] = new Register(0, 6, "$a2");
        registers[7] = new Register(0, 7, "$a3");

//                $t0-$t7 registers :
        registers[8] = new Register(0, 8, "$t0");
        registers[9] = new Register(0, 9, "$t1");
        registers[10] = new Register(0, 10, "$t2");
        registers[11] = new Register(0, 11, "$t3");
        registers[12] = new Register(0, 12, "$t4");
        registers[13] = new Register(0, 13, "$t5");
        registers[14] = new Register(0, 14, "$t6");
        registers[15] = new Register(0, 15, "$t7");

//                $s0-$s7 registers :
        registers[16] = new Register(0, 16, "$s0");
        registers[17] = new Register(0, 17, "$s1");
        registers[18] = new Register(0, 18, "$s2");
        registers[19] = new Register(0, 19, "$s3");
        registers[20] = new Register(0, 20, "$s4");
        registers[21] = new Register(0, 21, "$s5");
        registers[22] = new Register(0, 22, "$s6");
        registers[23] = new Register(0, 23, "$s7");

//                $t8-$t9 registers :
        registers[24] = new Register(0, 24, "$t8");
        registers[25] = new Register(0, 25, "$t9");
        registers[26] = new Register(0, 26, "");
        registers[27] = new Register(0, 27, "");
//                other registers :
        registers[28] = new Register(0, 28, "$gp");
        registers[29] = new Register(0, 29, "$sp");
        registers[30] = new Register(0, 30, "$fp");
        registers[31] = new Register(0, 31, "$ra");

    }

    public Register findRegister(String name) {
        for (int i = 0; i < 32; i++) {
            if (registers[i].getName().equals(name)) {
                return registers[i];
            }
        }
        return null;
    }

    int readData(int readRegister) {
        if (readRegister != 0)
            return (registers[readRegister].getData());
        return 0;
    }

    public int readData(String readRegister) {
        return (registers[baseChanger.changeToBaseTen(readRegister)].getData());
    }

    void writeData(int registerNumber, int writeData, int regWrite) {
        if (regWrite == 1) {
            registers[registerNumber].setData(writeData);
        }
    }

    public void writeData(String writeRegister, String writeData) {
//        if (writeData==1) {
//            registers[baseChanger.changeToBaseTen( writeRegister )].setData( baseChanger.changeToBaseTen( writeData ) );
//        }
    }

    public boolean isRegWrite() {
        return regWrite;
    }

    public void setRegWrite(boolean regWrite) {
        this.regWrite = regWrite;
    }
}
