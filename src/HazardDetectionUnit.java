class HazardDetectionUnit {

    boolean hasHazard(ID_ExPipelineRegister id_ex, IF_IDPipelineRegister if_id) {

        if (id_ex.MemRead == 1 && ((id_ex.instruction16_20.equals(if_id.instruction.getBits(6, 11)) || (id_ex.instruction16_20.equals(if_id.instruction.getBits(11, 16))))))
            return true;
        return false;
    }

}
