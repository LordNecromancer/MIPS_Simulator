import java.util.HashMap;

class DataMemory {
    HashMap<Integer, Integer> data = new HashMap<>();

    int readData(int aluResult) {
        if (data.get(aluResult) != null && data.containsKey(aluResult))
            return data.get(aluResult);
        return 0;
    }

    void writeData(int aluResult, Integer input) {
        data.put(aluResult, input);
    }
}
