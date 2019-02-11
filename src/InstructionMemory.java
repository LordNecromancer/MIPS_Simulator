import java.util.ArrayList;

 class InstructionMemory {

     ArrayList<Instruction> instructions=new ArrayList<>();

     Instruction getInstruction(int num){
        if(instructions.get(num)!=null){
            return instructions.get(num);
        }
        return null;
    }

     void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
