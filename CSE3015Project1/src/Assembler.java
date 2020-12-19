import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import jdk.nashorn.api.tree.ForInLoopTree;

public class Assembler{
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner input = new Scanner(file);
		
		while(input.hasNextLine()) {
			String str = input.nextLine();
			str = str.replaceAll(",", " ");
			String tokens[] = str.split(" ");
			
			String instruction = "";	
			switch (tokens[0].toUpperCase()) {
				case "AND":
					
					instruction += "0001";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[2].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[3].charAt(1) - '0',3);
					break;
				case "ANDI":
					
					instruction += "0010";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[2].charAt(1) - '0',3);
					if(tokens[3].toLowerCase().contains("x")) {
						tokens[3] = tokens[3].substring(2,tokens[3].length());
						instruction += hex2Binary(tokens[3], 6);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[3]), 6);
					}
					break;
					
				case "ADD":
					
					instruction += "0011";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[2].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[3].charAt(1) - '0',3);
					break;
				case "ADDI":
					
					instruction += "0100";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[2].charAt(1) - '0',3);
					if(tokens[3].toLowerCase().contains("x")) {
						tokens[3] = tokens[3].substring(2,tokens[3].length());
						instruction += hex2Binary(tokens[3], 6);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[3]), 6);
					}
					
					break;
					
				case "LD":
					
					instruction += "0101";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					if(tokens[2].toLowerCase().contains("x")) {
						tokens[2] = tokens[2].substring(2,tokens[2].length());
						instruction += hex2Binary(tokens[2], 9);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[2]), 9);
					}
					break;

				case "ST":
					instruction += "0110";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					if(tokens[2].toLowerCase().contains("x")) {
						tokens[2] = tokens[2].substring(2,tokens[2].length());
						instruction += hex2Binary(tokens[2], 9);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[2]), 9);
					}
					break;
					
				case "CMP":
					
					instruction += "0111";
					instruction += dec2Binary(tokens[1].charAt(1) - '0',3);
					instruction += dec2Binary(tokens[2].charAt(1) - '0',3);
					break;

				case "JUMP":
					
					instruction += "1000";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;

				case "JE":
					
					instruction += "1001";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;

				case "JA":
					instruction += "1010";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;

				case "JAE":
					instruction += "1011";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;

				case "JB":
					instruction += "1100";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;

				case "JBE":
					instruction += "1101";
					if(tokens[1].toLowerCase().contains("x")) {
						tokens[1] = tokens[1].substring(2,tokens[1].length());
						instruction += hex2Binary(tokens[1], 12);
					}
					else {
						instruction += dec2Binary(Integer.parseInt(tokens[1]), 12);
					}
					break;
					
			}
			while(instruction.length() < 16) {
				instruction += "0";
			}
			String hexInstruction = bin2Hex(instruction);
			System.out.println(hexInstruction);
		}
	}
	public static String dec2Binary(int decimalNumber, int size) {
        String binaryNumber = "";
        
        while (decimalNumber != 0) {
            binaryNumber = (decimalNumber % 2) + binaryNumber;
            decimalNumber /= 2;
        }
        
        while(binaryNumber.length() < size) {
        	binaryNumber = "0" + binaryNumber;
        }
        
        if (binaryNumber.length() > size) {
        	binaryNumber = binaryNumber.substring(binaryNumber.length() - size, binaryNumber.length());
        }
        
        return binaryNumber;
    }
	
	public static String hex2Binary(String hexNumber, int size) {
        String binaryNumber = "";

        for (int i = 0; i < hexNumber.length(); i++) {
            switch (hexNumber.charAt(i)) {
            case '0': binaryNumber = binaryNumber + "0000"; break;
            case '1': binaryNumber = binaryNumber + "0001"; break;
            case '2': binaryNumber = binaryNumber + "0010"; break;
            case '3': binaryNumber = binaryNumber + "0011"; break;
            case '4': binaryNumber = binaryNumber + "0100"; break;
            case '5': binaryNumber = binaryNumber + "0101"; break;
            case '6': binaryNumber = binaryNumber + "0110"; break;
            case '7': binaryNumber = binaryNumber + "0111"; break;
            case '8': binaryNumber = binaryNumber + "1000"; break;
            case '9': binaryNumber = binaryNumber + "1001"; break;
            case 'A': binaryNumber = binaryNumber + "1010"; break;
            case 'B': binaryNumber = binaryNumber + "1011"; break;
            case 'C': binaryNumber = binaryNumber + "1100"; break;
            case 'D': binaryNumber = binaryNumber + "1101"; break;
            case 'E': binaryNumber = binaryNumber + "1110"; break;
            case 'F': binaryNumber = binaryNumber + "1111"; break;
            }
        }
        
        while(binaryNumber.length() < size) {
        	binaryNumber = "0" + binaryNumber;
        }
        
        if (binaryNumber.length() > size) {// 0001 1010 1011
        	binaryNumber = binaryNumber.substring(binaryNumber.length() - size, binaryNumber.length());
        }
        
        return binaryNumber;
    }
	public static String bin2Hex(String binary){
        String hex = "" ;
        
        for (int i = 0; i< binary.length()/4 ; i++) {
            int value = bin2dec(binary.substring(4*i,(4*i)+4));
            if(value < 10 ) {
                hex += value;
            }
            else{
                hex += ((char) ((value-10) + 'A'));
            }

        }

        return hex;

    }
	public static int bin2dec(String value){
        int sum = 0;
        for (int i = 0; i < value.length() ; i++) {
            sum += value.charAt(i) == '1' ? Math.pow(2,value.length()-i-1) : 0;
        }
        return sum;
    }
}
