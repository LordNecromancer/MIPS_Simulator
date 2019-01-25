import java.util.ArrayList;

public class CPU {

    int clockNum = 0;
    int PC = 0;
    private ArrayList<Instruction> instructions;

    //RegisterFile registerMemory=new RegisterFile();
    private DataMemory dataMemory = new DataMemory();
    private ALU alu = new ALU();
    RegisterFile registerFile = new RegisterFile();
    private BaseChanger baseChanger = new BaseChanger();
    private HazardDetectionUnit hazardDetectionUnit = new HazardDetectionUnit();

    PipelineStage IF = new PipelineStage();
    PipelineStage ID = new PipelineStage();
    PipelineStage Ex = new PipelineStage();
    PipelineStage MEM = new PipelineStage();
    PipelineStage WB = new PipelineStage();

    IF_IDPipelineRegister IF_ID = new IF_IDPipelineRegister();
    ID_ExPipelineRegister ID_Ex = new ID_ExPipelineRegister();
    Ex_MEMPipelineRegister Ex_MEM = new Ex_MEMPipelineRegister();
    MEM_WBPipelineRegister MEM_WB = new MEM_WBPipelineRegister();
    private ForwardingUnit forwardingUnit = new ForwardingUnit(ID_Ex, Ex_MEM, MEM_WB);
    private int forwardA;
    private int forwardB;


    CPU(int clockNumber) {
        this.clockNum = clockNumber;

    }

    void run() {

        registerFile.registers[8].setData(10);


        for (int i = 0; i < clockNum; i++) {
            if (!WB.hadHazard) {
                runWBStage();
            }
            if (!MEM.hadHazard) {
                runMEMStage();
            }
            if (!Ex.hadHazard) {
                runEXStage();
            }
            if (!ID.hadHazard) {
                runIDStage();
            }
            if (!IF.hadHazard) {
                runIFStage();
            }

            readNextInstruction();
        }
    }

    private void readNextInstruction() {
        if (PC / 4 - 1 < instructions.size()) {


            if (instructions.get(PC / 4 - 1) != null) {

                IF.instruction = instructions.get(PC / 4 - 1);
            }

        } else {
            IF.instruction = null;
        }
        IF_ID.instruction = IF.instruction;
        IF_ID.PC = PC;
    }

    private void runIFStage() {
        if (hazardDetectionUnit.hasHazard(ID_Ex, IF_ID)) {
            IF_ID.instruction = null;
            PC -= 4;
        }
        if (IF_ID.instruction != null) {


            setControlData();
            ID_Ex.instruction21_25 = IF_ID.instruction.getBits(6, 11);
            ID_Ex.instruction16_20 = IF_ID.instruction.getBits(11, 16);
            ID_Ex.instruction11_15 = IF_ID.instruction.getBits(16, 21);
            ID_Ex.instruction0_15 = IF_ID.instruction.getBits(16, 32);
            readRegisters();
        } else {
            ID_Ex.MemToReg = 0;
            ID_Ex.instruction21_25 = null;
            ID_Ex.instruction16_20 = null;
            ID_Ex.instruction11_15 = null;
            ID_Ex.instruction0_15 = null;
            ID_Ex.PCSrc = 0;
            ID_Ex.MemRead = 0;
            ID_Ex.MemWrite = 0;
            ID_Ex.ALUOp0 = 0;
            ID_Ex.ALUOp1 = 0;
            ID_Ex.RegDst = 0;
            ID_Ex.RegWrite = 0;
            ID_Ex.ALUSrc = 0;
            ID_Ex.branch = 0;
            ID_Ex.registerData1 = 0;
            ID_Ex.registerData2 = 0;
        }
        ID_Ex.PC = IF_ID.PC;

        ID.instruction = IF.instruction;
    }

    private void readRegisters() {
        if (IF_ID.instruction.getType().equals("lw") || IF_ID.instruction.getType().equals("sw")) {
            ID_Ex.registerData1 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getC()).getNum());
            ID_Ex.registerData2 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getA()).getNum());

        } else if (IF_ID.instruction.getType().equals("beq")) {
            ID_Ex.registerData1 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getA()).getNum());
            ID_Ex.registerData2 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getB()).getNum());

        } else {
            ID_Ex.registerData1 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getB()).getNum());
            ID_Ex.registerData2 = registerFile.readData(registerFile.findRegister(IF_ID.instruction.getC()).getNum());
        }
    }

    private void setControlData() {
        ID_Ex.MemToReg = getMemToReg(IF_ID.instruction.getType());
        ID_Ex.PCSrc = getPCSrc(IF_ID.instruction.getType());
        ID_Ex.MemRead = getMemRead(IF_ID.instruction.getType());
        ID_Ex.MemWrite = getMemWrite(IF_ID.instruction.getType());
        ID_Ex.ALUOp0 = getALUOp0(IF_ID.instruction.getType());
        ID_Ex.ALUOp1 = getALUOp1(IF_ID.instruction.getType());
        ID_Ex.RegDst = getRegDst(IF_ID.instruction.getType());
        ID_Ex.RegWrite = getRegWrite(IF_ID.instruction.getType());
        ID_Ex.ALUSrc = getALUSrc(IF_ID.instruction.getType());
        ID_Ex.branch = getBranch(IF_ID.instruction.getType());
    }

    private void runIDStage() {

        Ex_MEM.instruction = baseChanger.changeToBaseTen(chooseInstruction(ID_Ex.instruction11_15, ID_Ex.instruction16_20));
        Ex_MEM.PC = ID_Ex.PC;
        Ex_MEM.MemToReg = ID_Ex.MemToReg;
        Ex_MEM.RegWrite = ID_Ex.RegWrite;
        Ex_MEM.PCSrc = ID_Ex.PCSrc;
        Ex_MEM.MemRead = ID_Ex.MemRead;
        Ex_MEM.MemWrite = ID_Ex.MemWrite;
        int ALUResult = alu.calculate(ID_Ex, Ex, forwardA, forwardB);
        Ex_MEM.ALUResult = ALUResult;
        Ex_MEM.branch = ID_Ex.branch;
        setEx_MEMRegisterData2();
        setEx_MEMZeroControlInstruction(ALUResult);
        Ex_MEM.addALU = 4 * (baseChanger.changeToBaseTen(ID_Ex.instruction0_15)) + ID_Ex.PC;
        Ex.instruction = ID.instruction;
    }

    private void setEx_MEMRegisterData2() {
        if (forwardB == 0) {
            Ex_MEM.readRegister2 = ID_Ex.registerData2;
        } else if (forwardB == 1) {
            Ex_MEM.readRegister2 = Ex.memoryResult;
        } else if (forwardB == 2) {
            Ex_MEM.readRegister2 = Ex.Ex_MEMALUResult;
        }
    }

    private void setEx_MEMZeroControlInstruction(int ALUResult) {
        if (ALUResult == 0) {
            Ex_MEM.zero = 1;
        } else {
            Ex_MEM.zero = 0;
        }
    }

    private void runMEMStage() {
        WB.instruction = MEM.instruction;
    }

    private void runEXStage() {
        forwardA = forwardingUnit.forwardA();
        forwardB = forwardingUnit.forwardB();
        MEM_WB.MemToReg = Ex_MEM.MemToReg;
        MEM_WB.RegWrite = Ex_MEM.RegWrite;
        MEM_WB.ALUResult = Ex_MEM.ALUResult;
        Ex.Ex_MEMALUResult = Ex_MEM.ALUResult;
        if (Ex_MEM.MemRead == 1) {
            MEM_WB.memoryData = dataMemory.readData(Ex_MEM.ALUResult);
        }
        if (Ex_MEM.MemWrite == 1) {
            dataMemory.writeData(Ex_MEM.ALUResult, Ex_MEM.readRegister2);
        }


        if (Ex_MEM.zero == 1 && Ex_MEM.branch == 1) {
            PC = Ex_MEM.addALU;
        } else {
            PC += 4;
        }
        MEM_WB.instruction = Ex_MEM.instruction;
        MEM.instruction = Ex.instruction;
    }

    private void runWBStage() {
        int MEM_WBResult = 0;


        if (MEM_WB.MemToReg == 0) {
            MEM_WBResult = MEM_WB.ALUResult;

        } else {
            MEM_WBResult = MEM_WB.memoryData;
        }
        Ex.memoryResult = MEM_WBResult;
        registerFile.writeData(MEM_WB.instruction, MEM_WBResult, MEM_WB.RegWrite);
    }

    private int getBranch(String type) {
        if (type.equals("beq"))
            return 1;
        return 0;
    }

    private int getALUSrc(String type) {
        if (type.equals("lw") || type.equals("sw"))
            return 1;
        return 0;
    }

    private int getRegWrite(String type) {
        if (type.equals("sw") || type.equals("beq"))
            return 0;
        return 1;
    }

    private int getRegDst(String type) {
        if (type.equals("lw"))
            return 0;
        return 1;
    }

    private int getALUOp1(String type) {
        if (type.equals("lw") || type.equals("sw") || type.equals("beq"))
            return 0;
        return 1;
    }

    private int getALUOp0(String type) {
        if (type.equals("beq"))
            return 1;
        return 0;
    }

    private int getMemWrite(String type) {
        if (type.equals("sw"))
            return 1;
        return 0;
    }

    private int getMemToReg(String type) {
        if (type.equals("lw"))
            return 1;
        return 0;
    }

    private int getMemRead(String type) {
        if (type.equals("lw"))
            return 1;
        return 0;
    }

    private int getPCSrc(String type) {
        return 0;
    }

    private String chooseInstruction(String instruction11_15, String instruction16_20) {
        if (ID_Ex.RegDst == 1) {
            return instruction11_15;
        }
        return instruction16_20;
    }

    public RegisterFile getRegisterFile() {
        return registerFile;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
