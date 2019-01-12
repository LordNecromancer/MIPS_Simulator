import java.util.ArrayList;

public class CPU {

    int clockNum=0;
    ArrayList<Instruction> instructions;

    RegisterMemory registerMemory=new RegisterMemory();

    PipelineStage IF=new PipelineStage();
    PipelineStage ID=new PipelineStage();
    PipelineStage Ex=new PipelineStage();
    PipelineStage MEM=new PipelineStage();
    PipelineStage WB=new PipelineStage();

    IF_IDPipelineRegister IF_ID=new IF_IDPipelineRegister();
    ID_ExPipelineRegister ID_Ex=new ID_ExPipelineRegister();
    Ex_MEMPipelineRegister Ex_MEM=new Ex_MEMPipelineRegister();
    MEM_WBPipelineRegister MEM_WB=new MEM_WBPipelineRegister();



    public CPU(int clockNumber,ArrayList<Instruction> instructions){
        this.clockNum=clockNumber;
        this.instructions=instructions;
        run();
    }

    private void run() {


        for(int i=0;i<clockNum;i++){
        if (!WB.hadHazard) {

            if (MEM_WB.MemToReg == 0) {
                registerMemory.writeRegister(MEM_WB.ALUResult);
            } else {
                registerMemory.writeRegister(MEM_WB.memoryData);
            }
        }
        if (!MEM.hadHazard) {

            WB.instruction = MEM.instruction;
            MEM_WB.MemToReg = Ex_MEM.MemToReg;
            MEM_WB.RegWrite = Ex_MEM.RegWrite;
            WB.instruction = MEM.instruction;

        }
        if (!Ex.hadHazard) {
            Ex_MEM.instruction = chooseInstruction(ID_Ex.instruction11_15, ID_Ex.instruction16_20);
            Ex_MEM.PC = ID_Ex.PC;
            Ex_MEM.MemToReg = ID_Ex.MemToReg;
            Ex_MEM.PCSrc = ID_Ex.PCSrc;
            Ex_MEM.MemRead = ID_Ex.MemRead;
            Ex_MEM.MemWrite = ID_Ex.MemWrite;
            MEM.instruction = Ex.instruction;

        }
        if (!ID.hadHazard) {
            ID_Ex.PC = IF_ID.PC;
            ID_Ex.MemToReg = getMemToReg(IF_ID.instruction.type);
            ID_Ex.instruction16_20 = IF_ID.instruction.getbits(16, 20);
            ID_Ex.instruction11_15 = IF_ID.instruction.getbits(11, 15);
            ID_Ex.instruction0_15 = IF_ID.instruction.getbits(0, 15);
            ID_Ex.PCSrc = getPCSrc(IF_ID.instruction.type);
            ID_Ex.MemRead = getMemRead(IF_ID.instruction.type);
            ID_Ex.MemWrite = getMemWrite(IF_ID.instruction.type);
            ID_Ex.ALUOp0 = getALUOp0(IF_ID.instruction.type);
            ID_Ex.ALUOp1 = getALUOp1(IF_ID.instruction.type);
            ID_Ex.RegDst = getRegDst(IF_ID.instruction.type);
            ID_Ex.RegWrite = getRegWrite(IF_ID.instruction.type);
            ID_Ex.ALUSrc = getALUSrc(IF_ID.instruction.type);


            Ex.instruction = ID.instruction;

        }

    }

    }

    private int getALUSrc(String type) {
        return 0;
    }

    private int getRegWrite(String type) {
        return 0;
    }

    private int getRegDst(String type) {
        return 0;
    }

    private int getALUOp1(String type) {
        return 0;
    }

    private int getALUOp0(String type) {
        return 0;
    }

    private int getMemWrite(String type) {
        return 0;
    }

    private int getMemToReg(String type) {
        return 0;
    }

    private int getMemRead(String type) {
        return 0;
    }

    private int getPCSrc(String type) {
        return 0;
    }

    private int chooseInstruction(int instruction11_15, int instruction16_20) {
        return 0;
    }
}
