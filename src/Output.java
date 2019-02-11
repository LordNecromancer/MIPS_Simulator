import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {
    CPU cpu;
    static BaseChanger baseChanger = new BaseChanger();
    int clockNum;
    String IF_Instruction = "00000000000000000000000000000000",
            ID_Instruction = "00000000000000000000000000000000",
            EX_Instruction = "00000000000000000000000000000000",
            MEM_Instruction = "00000000000000000000000000000000",
            WB_Instruction = "00000000000000000000000000000000";

    //IF_ID
    // none-controll
    int IF_ID_PC;
    String IF_ID_instruction;

    //    ID_EX
    // none-controll
    int ID_EX_PC, ID_EX_registerData1, ID_EX_registerData2;
    String ID_EX_instruction0_15, ID_EX_instruction11_15, ID_EX_instruction16_20;
    // controll
    int ID_EX_RegDst, ID_EX_RegWrite, ID_EX_ALUSrc, ID_EX_PCSrc, ID_EX_MemRead, ID_EX_MemWrite, ID_EX_MemToReg, ID_EX_ALUOp0, ID_EX_ALUOp1, ID_EX_branch;


    //    EX_MEM
    // none-controll
    int EX_MEM_PC, EX_MEM_ALUResult, EX_MEM_addALU, EX_MEM_readRegister2;
    String EX_MEM_instruction;
    // controll
    int EX_MEM_RegWrite, EX_MEM_PCSrc, EX_MEM_MemRead, EX_MEM_MemWrite, EX_MEM_MemToReg, EX_MEM_branch;


    //    MEM_WB
    // none-controll
    int MEM_WB_memoryData, MEM_WB_ALUResult;
    String MEM_WB_instruction;
    // controll
    int MEM_WB_RegWrite, MEM_WB_MemToReg;

    //register data
    int registerData1, registerData2;

    //    ALUs output
    int mainALUOutput, pcALUOutput, jumpALUOutput;

    //ALU Input
    int operand1,operand2;

    //    pc
    int PC;

    //    registers
    String $t0, $t1, $t2, $t3, $t4, $ra, $sp;

    Output(CPU cpu) throws IOException {
        clockNum = cpu.clockNum;

        try {
            IF_Instruction = cpu.IF.instruction.binary;
        } catch (NullPointerException ignored) {
            System.out.println( "null" );
        }
        try {
            ID_Instruction = cpu.ID.instruction.binary;
        } catch (NullPointerException ignored) {
            System.out.println( "null" );
        }
        try {
            EX_Instruction = cpu.Ex.instruction.binary;
        } catch (NullPointerException ignored) {
            System.out.println( "null" );
        }
        try {
            MEM_Instruction = cpu.MEM.instruction.binary;
        } catch (NullPointerException ignored) {
            System.out.println( "null" );
        }

        try {
            WB_Instruction = cpu.WB.instruction.binary;
        } catch (NullPointerException ignored) {
            System.out.println( "null" );
        }

//
//        if (cpu.IF.instruction.getType() == "") {
//            System.out.println( "null" );
//            IF_Instruction = "00000000000000000000000000000000";
//        }
//        if (cpu.ID.instruction.getType() == "") {
//            System.out.println( "null" );
//            ID_Instruction = "00000000000000000000000000000000";
//        }
//        if (cpu.Ex.instruction.getType() == "") {
//            System.out.println( "null" );
//            EX_Instruction = "00000000000000000000000000000000";
//        }
//        if (cpu.MEM.instruction.getType() == "") {
//            System.out.println( "null" );
//            MEM_Instruction = "00000000000000000000000000000000";
//        }
//        if (cpu.WB.instruction.getType() == "") {
//            System.out.println( "null" );
//            WB_Instruction = "00000000000000000000000000000000";
//        }


        try {

            IF_ID_instruction = cpu.IF_ID.instruction.binary;
        } catch (NullPointerException ignored) {
            IF_ID_instruction = "00000000000000000000000000000000";

        }


        IF_ID_PC = cpu.IF_ID.PC;



        ID_EX_PC = cpu.ID_Ex.PC;
        ID_EX_registerData1 = cpu.ID_Ex.registerData1;
        ID_EX_registerData2 = cpu.ID_Ex.registerData2;
        ID_EX_instruction0_15 = cpu.ID_Ex.instruction0_15;
        ID_EX_instruction11_15 = cpu.ID_Ex.instruction11_15;
        ID_EX_instruction16_20 = cpu.ID_Ex.instruction16_20;
        ID_EX_RegDst = cpu.ID_Ex.RegDst;
        ID_EX_RegWrite = cpu.ID_Ex.RegWrite;
        ID_EX_ALUSrc = cpu.ID_Ex.ALUSrc;
        ID_EX_PCSrc = cpu.ID_Ex.PCSrc;
        ID_EX_MemRead = cpu.ID_Ex.MemRead;
        ID_EX_MemWrite = cpu.ID_Ex.MemWrite;
        ID_EX_MemToReg = cpu.ID_Ex.MemToReg;
        ID_EX_ALUOp0 = cpu.ID_Ex.ALUOp0;
        ID_EX_ALUOp1 = cpu.ID_Ex.ALUOp1;
        ID_EX_branch = cpu.ID_Ex.branch;

        EX_MEM_PC = cpu.Ex_MEM.PC;
        EX_MEM_ALUResult = cpu.Ex_MEM.ALUResult;
        EX_MEM_instruction = baseChanger.Xbit( baseChanger.changeToBaseTwo( cpu.Ex_MEM.instruction ), 5 );
        EX_MEM_addALU = cpu.Ex_MEM.addALU;
        EX_MEM_readRegister2 = cpu.Ex_MEM.readRegister2;
        EX_MEM_RegWrite = cpu.Ex_MEM.RegWrite;
        EX_MEM_PCSrc = cpu.Ex_MEM.PCSrc;
        EX_MEM_MemRead = cpu.Ex_MEM.MemRead;
        EX_MEM_MemWrite = cpu.Ex_MEM.MemWrite;
        EX_MEM_MemToReg = cpu.Ex_MEM.MemToReg;
        EX_MEM_branch = cpu.Ex_MEM.branch;

        MEM_WB_memoryData = cpu.MEM_WB.memoryData;
        MEM_WB_ALUResult = cpu.MEM_WB.ALUResult;
        MEM_WB_instruction = baseChanger.Xbit( baseChanger.changeToBaseTwo( cpu.MEM_WB.instruction ), 5 );
        MEM_WB_RegWrite = cpu.IF_ID.RegWrite;
        MEM_WB_MemToReg = cpu.IF_ID.MemToReg;

        registerData1 = cpu.ID_Ex.registerData1;
        registerData2 = cpu.ID_Ex.registerData2;


        PC = cpu.PC;
        $t0 = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$t0" ).getData() );
        $t0 = baseChanger.Xbit( $t0, 32 );
        $t1 = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$t1" ).getData() );
        $t1 = baseChanger.Xbit( $t1, 32 );
        $t2 = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$t2" ).getData() );
        $t2 = baseChanger.Xbit( $t2, 32 );
        $t3 = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$t3" ).getData() );
        $t3 = baseChanger.Xbit( $t3, 32 );
        $t4 = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$t4" ).getData() );
        $t4 = baseChanger.Xbit( $t4, 32 );
        $ra = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$ra" ).getData() );
        $ra = baseChanger.Xbit( $ra, 32 );
        $sp = baseChanger.changeToBaseTwo( cpu.registerFile.findRegister( "$sp" ).getData() );
        $sp = baseChanger.Xbit( $sp, 32 );

        mainALUOutput = EX_MEM_ALUResult;
        jumpALUOutput = EX_MEM_addALU;
        pcALUOutput = IF_ID_PC;

        operand1=cpu.operand1;
        operand2=cpu.operand2;

        getOutput( cpu );

    }

    private void getOutput(CPU cpu) throws IOException {
        try (PrintWriter out = new PrintWriter( "result.txt" )) {
            out.println( "clock number : " + clockNum + "     PC : " + PC );
            out.println();
            out.println( " IF Instruction " + IF_Instruction );
            out.println( " ID Instruction " + ID_Instruction );
            out.println( " EX Instruction " + EX_Instruction );
            out.println( " MEM Instruction " + MEM_Instruction );
            out.println( " WB Instruction " + WB_Instruction );
            out.println( "_____________________________________________________________________________" );
            out.println( "IF_ID Pipeline register information :" );
            out.println();
            out.println( "     PC : " + IF_ID_PC + "     instruction : " + IF_ID_instruction );
            out.println( "_____________________________________________________________________________" );
            out.println( "ID_EX Pipeline register information :" );
            out.println();
            out.println( "     PC : " + ID_EX_PC );
            out.println( "     registerData1 : " + ID_EX_registerData1 + "     registerData2 : " + ID_EX_registerData2 );
            out.println( "     instruction 0-15 : " + ID_EX_instruction0_15 + "     instruction 11-15 : " + ID_EX_instruction11_15 + "     instruction 16-20 : " + ID_EX_instruction16_20 );
            out.println( "     RegDst : " + ID_EX_RegDst + "  RegWrite : " + ID_EX_RegWrite + "  ALUSrc : " + ID_EX_ALUSrc + "  PCSrc : " + ID_EX_PCSrc + "  MemRead : " + ID_EX_MemRead );
            out.println( "     MemWrite : " + ID_EX_MemWrite + "  MemToReg : " + ID_EX_MemToReg + "  ALUOp0 : " + ID_EX_ALUOp0 + "  ALUOp1 : " + ID_EX_ALUOp1 + "  branch : " + ID_EX_branch );
            out.println( "_____________________________________________________________________________" );
            out.println( "EX_MEM Pipeline register information :" );
            out.println();
            out.println( "     PC : " + EX_MEM_PC + "     instruction : " + EX_MEM_instruction );
            out.println( "     ALU result : " + EX_MEM_ALUResult + "     add Result : " + EX_MEM_addALU + "     readRegister2 : " + EX_MEM_readRegister2 );
            out.println( "     RegWrite : " + EX_MEM_RegWrite + "  PCSrc : " + EX_MEM_PCSrc + "  MemRead : " + EX_MEM_MemRead );
            out.println( "     MemWrite : " + EX_MEM_MemWrite + "  MemToReg : " + EX_MEM_MemToReg + "  branch : " + EX_MEM_branch );
            out.println( "_____________________________________________________________________________" );
            out.println( "MEM_WB Pipeline register information :" );
            out.println();
            out.println( "     memory data : " + MEM_WB_memoryData + "     instruction : " + MEM_WB_instruction + "     ALU result : " + MEM_WB_ALUResult );
            out.println( "     RegWrite : " + MEM_WB_RegWrite );
            out.println( "     MemToReg : " + MEM_WB_MemToReg );
            out.println( "_____________________________________________________________________________" );
            out.println( "Register File : " );
            out.println();
            out.println( "     ReadData1 : " + registerData1 + "     ReadData2 : " + registerData2 );
            out.println( "     $t0 : " + $t0 + "    =  " + cpu.registerFile.findRegister( "$t0" ).getData() );
            out.println( "     $t1 : " + $t1 + "    =  " + cpu.registerFile.findRegister( "$t1" ).getData() );
            out.println( "     $t2 : " + $t2 + "    =  " + cpu.registerFile.findRegister( "$t2" ).getData() );
            out.println( "     $t3 : " + $t3 + "    =  " + cpu.registerFile.findRegister( "$t3" ).getData() );
            out.println( "     $t4 : " + $t4 + "    =  " + cpu.registerFile.findRegister( "$t4" ).getData() );
            out.println( "     $ra : " + $ra + "    =  " + cpu.registerFile.findRegister( "$ra" ).getData() );
            out.println( "     $sp : " + $sp + "    =  " + cpu.registerFile.findRegister( "$sp" ).getData() );
            out.println( "_____________________________________________________________________________" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Desktop.getDesktop().open(new File("C:/Users/Sun/IdeaProjects/MIPS_Simulator/result.txt "));
    }
}
