class ForwardingUnit {

    private ID_ExPipelineRegister id_ex;
    private Ex_MEMPipelineRegister ex_mem;
    private MEM_WBPipelineRegister mem_wb;
    private BaseChanger baseChanger = new BaseChanger();

    ForwardingUnit(ID_ExPipelineRegister id_ex, Ex_MEMPipelineRegister ex_mem, MEM_WBPipelineRegister mem_wb) {

        this.id_ex = id_ex;
        this.ex_mem = ex_mem;
        this.mem_wb = mem_wb;
    }


    int forwardA() {

        int forward = 0;
         if (ex_mem.RegWrite == 1 && ex_mem.instruction != 0 && ex_mem.instruction == baseChanger.changeToBaseTen(id_ex.instruction21_25)) {
            forward = 2;
        }
       else if (mem_wb.RegWrite == 1 && mem_wb.instruction != 0 && mem_wb.instruction == baseChanger.changeToBaseTen(id_ex.instruction21_25) ) {
             //&& !(ex_mem.RegWrite == 1 && (ex_mem.instruction != 0) && (ex_mem.instruction != baseChanger.changeToBaseTen(id_ex.instruction21_25)))
            forward = 1;
        }
        return forward;
    }

    int forwardB() {
        int forward = 0;
         if (ex_mem.RegWrite == 1 && ex_mem.instruction != 0 && ex_mem.instruction == baseChanger.changeToBaseTen(id_ex.instruction16_20)) {
            forward = 2;
        }
       else if (mem_wb.RegWrite == 1 && mem_wb.instruction != 0 && mem_wb.instruction == baseChanger.changeToBaseTen(id_ex.instruction16_20) ) {
          //   && !(ex_mem.RegWrite == 1 && (ex_mem.instruction != 0) && (ex_mem.instruction != baseChanger.changeToBaseTen(id_ex.instruction21_25)))
            forward = 1;
        }
        return forward;
    }

}
