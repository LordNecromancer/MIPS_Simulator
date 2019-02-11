import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static CPU cpu;
    private static InstructionMemory instructionMemory=new InstructionMemory();
    private static BaseChanger baseChanger = new BaseChanger();

    public static void main(String[] args) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        instructions.add(null);
        instructions.add(null);
        instructions.add(null);


        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        JFrame load = new JFrame();
        load.setSize(650, 650);
        load.setLocation(650, 300);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(selectedFile.getPath());
                BufferedReader br = new BufferedReader(new FileReader(selectedFile.getPath()));
                String line = br.readLine();
                while (line != null) {
                    strings.add(line);
                    line = br.readLine();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        int clockNum = Integer.parseInt(JOptionPane.showInputDialog("Clock : "));
        cpu = new CPU(clockNum);

        stringToInstruction(strings, instructions);
        cpu.instructionMemory=instructionMemory;
        cpu.run();
        Output output = new Output(cpu);
        CPUGraphics cpuGraphics=new CPUGraphics(output);

    }


    private static void stringToInstruction(ArrayList<String> strings, ArrayList<Instruction> instructions) {
        for (int i = 0; i < strings.size() - 1; i++) {
            String type = " ";
            if (!strings.get(i).contains(":")) {
                if (strings.get(i).contains(" ")) {
                    type = strings.get(i).substring(0, strings.get(i).indexOf(" "));

                }
                String a = "", b = "", c = "";
                Instruction instruction = null;
                if (type.equals("add") || type.equals("sub") || type.equals("and") || type.equals("or") || type.equals("nor") || type.equals("slt") || type.equals("beq")) {
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf(" ") + 1));
                    a = strings.get(i).substring(0, strings.get(i).indexOf(","));
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf(",") + 1));
                    b = strings.get(i).substring(0, strings.get(i).indexOf(","));
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf(",") + 1));
                    if (type.equals("beq")) {
                        if (isNumeric(strings.get(i))) {
                            c = strings.get(i);
                        } else {
                            String temp = strings.get(i);
                            int k = 0;
                            for (int j = i; j < strings.size(); j++) {
                                if (strings.get(j).startsWith(temp)) {

                                    c = String.valueOf(4 * k-4);
                                }
                                k++;
                            }
                        }
                    }else{
                        c = strings.get(i);
                    }
                    System.out.println("c           " + c);
                    instruction = new Instruction(type, a, b, c);
                    instruction.binary = getBinary(instruction);
                    instructions.add(instruction);
                }
                if (type.equals("lw") || type.equals("sw")) {
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf(" ") + 1));
                    a = strings.get(i).substring(0, strings.get(i).indexOf(","));
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf(",") + 1));
                    b = strings.get(i).substring(0, strings.get(i).indexOf("("));
                    strings.set(i, strings.get(i).substring(strings.get(i).indexOf("(") + 1));
                    c = strings.get(i).substring(0, strings.get(i).indexOf(")"));
                    instruction = new Instruction(type, a, b, c);
                    instruction.binary = getBinary(instruction);
                    instructions.add(instruction);
                }

                instructionMemory.setInstructions(instructions);
            }
        }
    }

    public static String getBinary(Instruction instruction) {
        String type = instruction.getType();
        String a = instruction.getA();
        String b = instruction.getB();
        String c = instruction.getC();
        String binarycode = "";
        String field1 = "", field2 = "", field3 = "", field4 = "", field5 = "", field6 = "";
        if (type.equals("add") || type.equals("sub") || type.equals("and") || type.equals("or") || type.equals("nor") || type.equals("slt")) {
            field1 = baseChanger.changeToBaseTwo(0);
            field1 = baseChanger.Xbit(field1, 6);
            field2 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(b).getNum());
            field2 = baseChanger.Xbit(field2, 5);
            field3 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(c).getNum());
            field3 = baseChanger.Xbit(field3, 5);
            field4 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(a).getNum());
            field4 = baseChanger.Xbit(field4, 5);
            field5 = baseChanger.changeToBaseTwo(0);
            field5 = baseChanger.Xbit(field5, 5);
            switch (type) {
                case "add":
                    field6 = baseChanger.changeToBaseTwo(32);
                    break;
                case "sub":
                    field6 = baseChanger.changeToBaseTwo(34);
                    break;
                case "and":
                    field6 = baseChanger.changeToBaseTwo(36);
                    break;
                case "or":
                    field6 = baseChanger.changeToBaseTwo(37);
                    break;
                case "nor":
                    field6 = baseChanger.changeToBaseTwo(39);
                    break;
                case "slt":
                    field6 = baseChanger.changeToBaseTwo(42);
                    break;
            }
            field6 = baseChanger.Xbit(field6, 6);
            binarycode = field1 + field2 + field3 + field4 + field5 + field6;
        }
        if (type.equals("beq")) {
            field1 = baseChanger.changeToBaseTwo(4);
            field1 = baseChanger.Xbit(field1, 6);
            field2 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(a).getNum());
            field2 = baseChanger.Xbit(field2, 5);
            field3 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(b).getNum());
            field3 = baseChanger.Xbit(field3, 5);
            field4 = baseChanger.changeToBaseTwo(Integer.parseInt(c) / 4);
            field4 = baseChanger.Xbit(field4, 16);
            binarycode = field1 + field2 + field3 + field4;
        }
        if (type.equals("lw") || type.equals("sw")) {
            if (type.equals("lw")) {
                field1 = baseChanger.changeToBaseTwo(35);
            }
            if (type.equals("sw")) {
                field1 = baseChanger.changeToBaseTwo(43);
            }
            field1 = baseChanger.Xbit(field1, 6);
            field2 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(c).getNum());
            field2 = baseChanger.Xbit(field2, 5);
            field3 = baseChanger.changeToBaseTwo(cpu.getRegisterFile().findRegister(a).getNum());
            field3 = baseChanger.Xbit(field3, 5);
            field4 = baseChanger.changeToBaseTwo(Integer.parseInt(b));
            field4 = baseChanger.Xbit(field4, 16);
            binarycode = field1 + field2 + field3 + field4;
        }
        return binarycode;
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
