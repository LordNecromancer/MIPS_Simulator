public class ALU {
    private ID_ExPipelineRegister id_ex;
    private PipelineStage ex;
    private BaseChanger baseChanger = new BaseChanger();
    private int forwardA = 0;
    private int forwardB = 0;

    int calculate(ID_ExPipelineRegister id_ex, PipelineStage ex, int forwardA, int forwardB) {
        this.forwardA = forwardA;
        this.forwardB = forwardB;
        this.id_ex = id_ex;
        this.ex = ex;
        int result = 0;
        String aluControl = getMainALUControl(id_ex);

        result = runMainALU(aluControl);
        return result;
    }

    private String getMainALUControl(ID_ExPipelineRegister id_ex) {
        String functField = "0";
        if (id_ex.instruction0_15 != null) {
            functField = id_ex.instruction0_15.substring(10);
        }
        String aluControl = "";
        if (id_ex.ALUOp0 == 0 && id_ex.ALUOp1 == 0)
            aluControl = "0010";
        else if (id_ex.ALUOp0 == 1 && id_ex.ALUOp1 == 0)
            aluControl = "0110";

        else if (id_ex.ALUOp0 == 0 && id_ex.ALUOp1 == 1) {
            switch (functField) {
                //add
                case ("100000"):
                    aluControl = "0010";
                    break;
                case ("100010"):
                    aluControl = "0110";
                    break;
                case ("100100"):
                    aluControl = "0000";
                    break;
                case ("100101"):
                    aluControl = "0001";
                    break;
                case ("101010"):
                    aluControl = "0111";
            }

        }
        return aluControl;
    }

    private int runMainALU(String aluControl) {

        int operand1 = getOperand1();
        int result = 0;
        int operand2 = getOperand2();
        // System.out.println(operand1 + "    " + operand2);


        switch (aluControl) {
            case "0010":
                result = add(operand1, operand2);
                break;
            case "0110":
                result = sub(operand1, operand2);
                break;
            case "0000":
                result = AND(operand1, operand2);
                break;
            case "0001":
                result = OR(operand1, operand2);
                break;
            case "0111":
                result = slt(operand1, operand2);
        }
        return result;


    }

    private int getOperand1() {
        int operand1 = 0;
        if (forwardA == 0) {
            operand1 = id_ex.registerData1;

        } else if (forwardA == 2) {
            operand1 = ex.Ex_MEMALUResult;
        } else if (forwardA == 1) {
            operand1 = ex.memoryResult;

        }
        return operand1;
    }

    private int getOperand2() {
        int operand2 = 0;
        if (forwardB == 0) {
            if (id_ex.ALUSrc == 0) {
                operand2 = id_ex.registerData2;
            } else {
                operand2 = baseChanger.changeToBaseTen(id_ex.instruction0_15);
            }
        } else if (forwardB == 2) {
            operand2 = ex.Ex_MEMALUResult;
        } else if (forwardB == 1) {
            operand2 = ex.memoryResult;
        }
        return operand2;
    }

    private int add(int operand1, int operand2) {
        return operand1 + operand2;
    }

    private int sub(int operand1, int operand2) {
        return operand1 - operand2;
    }

    private int AND(int operand1, int operand2) {
        return operand1 & operand2;
    }

    private int OR(int operand1, int operand2) {
        return operand1 | operand2;
    }

    private int slt(int operand1, int operand2) {
        if (operand1 < operand2)
            return 1;
        return 0;
    }
}
