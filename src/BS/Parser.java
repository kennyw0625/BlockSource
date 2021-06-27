package BS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;

public class Parser {
	public static int lineNumber = 0;
	public static int numberOfLines = 0;
	public static int blockIndex = 0;
	public static boolean isInsideForLoop = false;

	public static void parse(String [] File) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		Main.ConsoleTextArea.append("====== Running " + "IN IDE CODE" + " at " + dtf.format(now) + " ======\n");
		try{
			numberOfLines = File.length;
			while (lineNumber <= numberOfLines){
				String curLine = File[lineNumber];
				determine(curLine);
				++lineNumber;
			}
		}
		catch (Exception Ex){
		}
		Main.ConsoleTextArea.append(" \n=== END OF PROGRAM ===\n\n\n\n");
		lineNumber = 0;
	}

	public static String Remove(int s, int e, String line) {
		String output = line.substring(0, s) + line.substring(e, line.length());
		return output; 
	}

	public static void determine(String curLine) throws IOException {
		if (curLine.startsWith("println")) {
			basics.println(curLine);
		} else if (curLine.startsWith("printvar")) {
			basics.printvar(curLine);
		} else if (curLine.startsWith("print")) {
			basics.print(curLine);
		} else if (curLine.startsWith("input")) {
			basics.input(curLine);
		} else if (curLine.startsWith("add")) {
			basics.add(curLine);
		} else if (curLine.startsWith("subtract")) {
			basics.subtract(curLine);
		} else if (curLine.startsWith("multiply")) {
			basics.multiply(curLine);
		} else if (curLine.startsWith("divide")) {
			basics.divide(curLine);
		} else if (curLine.startsWith("modulo")) {
			basics.modulo(curLine);
		}
		/*
	            else if (curLine.StartsWith("squareRoot")){
	                basics.squareRoot(curLine);
	            }else if (curLine.StartsWith("power")){
	                basics.power(curLine);
	            }else if (curLine.StartsWith("randomInteger")){
	                basics.randomInteger(curLine);
	            }else if (curLine.StartsWith("randomDecimal")){
	                basics.randomDecimal(curLine);
	            }
		 */
		//data
		else if (curLine.startsWith("string")){
			data.newString(curLine);
		}else if (curLine.startsWith("int")){
			data.newInt(curLine);
		}else if (curLine.startsWith("long")){
			data.newLong(curLine);
		}else if (curLine.startsWith("double")){
			data.newDouble(curLine);
		}else if (curLine.startsWith("boolean")){
			data.newBoolean(curLine);
		}else if (curLine.startsWith("for")){
			logic.newFor(curLine);
		}else if (curLine.startsWith("while")){ 
			logic.newWhile(curLine);
		} else if (curLine.startsWith("file") || curLine.startsWith("folder")){
			file.determine(curLine);
		}
		else if (curLine.startsWith("ui")){

		}
	}

	public static class basics {
		public static void print(String curLine) {
			curLine = Remove(0, 7, curLine);
			String newCurLine = Remove(curLine.length() - 3, curLine.length(), curLine);
			Main.ConsoleTextArea.append(newCurLine+"\n");
			drawButton.print(newCurLine);
		}

		public static void println(String curLine) {
			curLine = Remove(0, 9, curLine);
			String newCurLine = Remove(curLine.length() - 3, curLine.length(), curLine);
			Main.ConsoleTextArea.append(newCurLine+"\n");
			drawButton.println(newCurLine);
		}

		public static void printvar(String curLine){
			curLine = curLine.replaceAll(" ", "");
			curLine = Remove(0, 9, curLine);
			String newCurLine = Remove(curLine.length() - 1, curLine.length(), curLine);
			String getVariable = bs.storedVariables.get(newCurLine);
			System.out.println(bs.storedVariables);
			if (getVariable.startsWith("string")) {
				getVariable = Remove(0, 6, getVariable);
			} else if (getVariable.startsWith("int")) {
				getVariable = Remove(0, 3, getVariable);
			} else if (getVariable.startsWith("long")) {
				getVariable = Remove(0, 4, getVariable);
			} else if (getVariable.startsWith("double")) {
				getVariable = Remove(0, 6, getVariable);
			} else if (getVariable.startsWith("boolean")) {
				getVariable = Remove(0, 7, getVariable);
			}
			getVariable = getVariable.substring(2);
			getVariable = Remove(getVariable.length() - 2, getVariable.length(), getVariable);
			Main.ConsoleTextArea.append(getVariable.trim()+"\n");
			drawButton.printvar(newCurLine);
		}
		public static void input(String curLine) {
			//await Task.Delay(1000);
		}

		public static void add(String curLine) {
			curLine = Remove(0, 4, curLine);
			curLine = Remove(curLine.length() - 2, curLine.length(), curLine);
			String[] toBeAdded = curLine.replaceAll(" ", "").split(",");
			double last = 0;
			for (int i = 0; i < toBeAdded.length; i++) {
				last += Double.parseDouble(toBeAdded[i]);
			}
			Main.ConsoleTextArea.append(last+"\n");
			drawButton.addB(toBeAdded);
		}

		public static void subtract(String curLine) {
			curLine = Remove(0, 9, curLine);
			curLine = Remove(curLine.length() - 2, curLine.length(), curLine);
			String[] toBeSubtracted = curLine.replaceAll(" ", "").split(",");
			double last = Double.parseDouble(toBeSubtracted[1])-Double.parseDouble(toBeSubtracted[0]);
			Main.ConsoleTextArea.append(last+"\n");
			drawButton.subB(toBeSubtracted);
		}

		public static void multiply(String curLine) {
			curLine = Remove(0, 9, curLine);
			curLine = Remove(curLine.length() - 2, curLine.length(), curLine);
			String[] toBeMultiplied = curLine.replaceAll(" ", "").split(",");
			double last = 1;
			for(int i = 0; i < toBeMultiplied.length; ++i) {
				last *= Double.parseDouble(toBeMultiplied[i]);
			}
			Main.ConsoleTextArea.append(last+"\n");
			drawButton.multiB(toBeMultiplied);
		}

		public static void divide(String curLine) {
			curLine = Remove(0, 7, curLine);
			curLine = Remove(curLine.length() - 2, curLine.length(), curLine);
			String[] toBeDivided = curLine.replaceAll(" ", "").split(",");
			double last = Double.parseDouble(toBeDivided[0]) / Double.parseDouble(toBeDivided[1]);
			Main.ConsoleTextArea.append(last+"\n");
			drawButton.divB(toBeDivided);
		}

		public static void modulo(String curLine) {
			curLine = Remove(0, 7, curLine);
			curLine = Remove(curLine.length() - 2, curLine.length(), curLine);
			String[] toBeModulo = curLine.replaceAll(" ", "").split(",");
			double last = Double.parseDouble(toBeModulo[0]) % Double.parseDouble(toBeModulo[1]);
			Main.ConsoleTextArea.append(last+"\n");
			drawButton.modB(toBeModulo);
		}
	}

	public static class data {
		public static void newString(String curLine) {
			String type = "string";
			curLine = Remove(0, 7, curLine);
			String[] varName = curLine.split("=");
			String finalString = (type + "-" + varName[1].trim());
			bs.storedVariables.put(varName[0].trim(), finalString);
			drawButton.StringB(varName[0], varName[1]);
		}

		public static void newInt(String curLine) {
			String type = "int";
			curLine = Remove(0, 4, curLine);
			String[] varName = curLine.split("=");
			String finalString = (type + "-" + varName[1].trim());
			bs.storedVariables.put(varName[0].trim(), finalString);
			drawButton.intB(varName[0], varName[1]);
		}

		public static void newLong(String curLine) {
			String type = "long";
			curLine = Remove(0, 5, curLine);
			String[] varName = curLine.split("=");
			String finalString = (type + "-" + varName[1].trim());
			bs.storedVariables.put(varName[0].trim(), finalString);
			drawButton.longB(varName[0], varName[1]);
		}

		public static void newDouble(String curLine) {
			String type = "double";
			curLine = Remove(0, 7, curLine);
			String[] varName = curLine.split("=");
			String finalString = (type + "-" + varName[1].trim());
			bs.storedVariables.put(varName[0].trim(), finalString);
			drawButton.doubleB(varName[0], varName[1]);
		}

		public static void newBoolean(String curLine) {
			String type = "boolean";
			curLine = Remove(0, 8, curLine);
			String[] varName = curLine.split("=");
			String finalString = (type + "-" + varName[1].trim());
			bs.storedVariables.put(varName[0].trim(), finalString);
			drawButton.booleanB(varName[0], varName[1]);
		}
	}

	public static class logic {
		public static void newFor(String curLine) throws IOException   {
			String[] PARAMETERS_CODETOLOOP = curLine.split("::");
			PARAMETERS_CODETOLOOP[0].trim();
			String[] MULTIPLE_FUNCTIONS = PARAMETERS_CODETOLOOP[1].split(":");
			PARAMETERS_CODETOLOOP[0].replace("for", "");
			PARAMETERS_CODETOLOOP[0].replace("in", "");
			PARAMETERS_CODETOLOOP[0].replace("range", "");
			PARAMETERS_CODETOLOOP[0].replace(" ", "");
			String[] VARNAME_CONSTRAINTS = PARAMETERS_CODETOLOOP[0].split("\\(");
			VARNAME_CONSTRAINTS[1] = VARNAME_CONSTRAINTS[1].substring(0, VARNAME_CONSTRAINTS[1].length()-2);
			String[] MIN_MAX = VARNAME_CONSTRAINTS[1].split(",");
			int min = Integer.parseInt(MIN_MAX[0].trim());
			int max = Integer.parseInt(MIN_MAX[1].trim());
			int timesToLoop = max - min + 1;
			isInsideForLoop = true;
			for (int i = 0; i < timesToLoop; ++i){
				for (int ii = 0; ii < MULTIPLE_FUNCTIONS.length; ++ii){
					determine(MULTIPLE_FUNCTIONS[ii].trim());
					System.out.println(i + ":" + MULTIPLE_FUNCTIONS[ii].trim());
				}
			}
			isInsideForLoop = false;

			drawButton.forB(timesToLoop, MULTIPLE_FUNCTIONS);
			System.out.println("Button done");



			/*
	                String[] seperatedA = curLine.split(":");    // A[0 // 1] [ for (int i = 0, i < 10, ++i // println("h i"); );]
	                seperatedA[0].replace(" ", "").Remove(0, 4).trim();    // A[0 // 1] [inti=0,i<10,++i// println("h i"); );]
	                seperatedA[1].Remove(seperatedA[1].Length - 2).trim(); // A[0 // 1] [inti=0,i<10,++i//println("h i");] //END RESULT
	                String[] seperatedB = seperatedA[0].split(","); // B [0 // 1 // 2] [inti=0//i<10//++i]
	                String type;
	                if (seperatedB[0].StartsWith("int"))
	                {
	                    type = "int";
	                    seperatedB[0] = seperatedB[0].Remove(0, 3); // B [0 // 1 // 2] [i=0//i<10//++i]
	                }
	                else if (seperatedB[0].StartsWith("double"))
	                {
	                    type = "double";
	                    seperatedB[0] = seperatedB[0].Remove(0, 6);
	                }

	                String[] temp = seperatedB[0].split("="); // temp [0 // 1] [i//0]
	                String varName = temp[0];
	                double startNum = Double.parseDouble(temp[1]);
	                double constraint = Double.parseDouble(seperatedB[1].Remove(0, varName.Length)); // B [0 // 1 // 2] [i=0//<10//++i]
	                double interval = 0;
	                if (seperatedB[2].Contains("++"))
	                {
	                    interval = 1.0;
	                }
	                else if (seperatedB[2].Contains("--"))
	                {
	                    interval = -1.0;
	                }
	                else if (seperatedB[2].Contains("+="))
	                {
	                    interval = Double.parseDouble(seperatedA[2].Remove(0, varName.Length).replace("+=", ""));
	                }
	                else if (seperatedB[2].Contains("-="))
	                {
	                    interval = 0 - Double.parseDouble(seperatedA[2].Remove(0, varName.Length).replace("-=", ""));
	                }

	                for (double i = startNum; i < constraint; i += interval)
	                {
	                    scanCode.determine(seperatedA[1]);
	                }
			 */
		}

		public static void newWhile(String curLine) throws IOException {
			String[] PARAMETERS_CODETOLOOP = curLine.split("::");
			PARAMETERS_CODETOLOOP[0].trim();
			String[] MULTIPLE_FUNCTIONS = PARAMETERS_CODETOLOOP[1].split(":");

			isInsideForLoop = true;
			for (int i = 0; i < MULTIPLE_FUNCTIONS.length; ++i) {
				determine(MULTIPLE_FUNCTIONS[i].trim());
			}
			isInsideForLoop = false;

			drawButton.whileB(PARAMETERS_CODETOLOOP[0].replace("while ", "").trim(), MULTIPLE_FUNCTIONS, curLine);
		}

	}

	public static class file {
		public static void determine(String curLine) throws IOException {
			if (curLine.startsWith("file.create")) {
				curLine = Remove(0, 13, curLine);
				curLine = Remove(curLine.length() - 3, curLine.length(), curLine);
				create(curLine);
			} else if (curLine.startsWith("file.append"))  {
				curLine = Remove(0, 13, curLine);
				curLine = Remove(curLine.length() - 3, curLine.length(), curLine);
				append(curLine);
			} else if (curLine.startsWith("file.read")) {
				curLine = Remove(0, 11, curLine);
				curLine = Remove(curLine.length() - 3, curLine.length(), curLine);
				read(curLine);
			} else if (curLine.startsWith("folder.create")) {
				curLine = Remove(0, 15, curLine);
				curLine = Remove(curLine.length() - 3, curLine.length(), curLine); 
				folder(curLine);
			}
		}

		public static void create(String path){
			try {
				@SuppressWarnings("unused")
				File fs = new File(path);
			}catch (Exception Ex) {
				Main.ConsoleTextArea.append("Error occured creating file " + path + "\n");
			}
			drawButton.createFileB(path);
		}

		public static void append(String path) {
			String[] splitted = path.split(",");
			splitted[0] = splitted[0].replaceAll("\"", "");
			splitted[1] = Remove(0, 2, splitted[1]); 
			try {
				FileWriter fw = new FileWriter(splitted[0]);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.append(splitted[1]);
				bw.close();
			}catch (Exception Ex) {
				Main.ConsoleTextArea.append("Error occured writint to file " + path + "\n");
			}
			drawButton.writeFileB(splitted[1], splitted[0]);

		}

		public static void read(String path) throws IOException {
			String[] splitted = path.split(",");
			splitted[0] = splitted[0].replaceAll("\"", "");
			splitted[1] = splitted[1].trim();
			splitted[1] = splitted[1].substring(1, splitted[1].length());
			splitted[1] = splitted[1].replaceAll(" ", "");
			int lineNumber = Integer.parseInt(splitted[1]) - 1;
			BufferedReader br = new BufferedReader(new FileReader(splitted[0]));
			try {
				for(int i = 0; i < lineNumber; i++) {
					br.readLine();
				}
				Main.ConsoleTextArea.append(br.readLine() + "\n");
			}catch (Exception Ex) {
				Main.ConsoleTextArea.append("Error occured reading from file " + path + "\n");
			}
			br.close();
			drawButton.readFileB(String.valueOf(lineNumber), splitted[0]);
		}

		public static void folder(String path) {
			File f = new File(path);
			if(!f.mkdir()) {
				Main.ConsoleTextArea.append("Error occured creating directory " + path + "\n");
			}
			drawButton.createFolderB(path);
		}
	}

	public static class drawButton {
		static double resized25a = (Main.frame.getWidth() * 0.25);
		static int resized25 = (int)resized25a;
		static double resized75a = (Main.frame.getWidth() * 0.75);
		static int resized75 = (int)resized75a;

		public static void print(String newCurLine) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 30));
				newBlock.setText("Output " + newCurLine);
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				blockIndex++;
			}
		}
		public static void println(String newCurLine) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 30));
				newBlock.setText("Output " + newCurLine + " to a new line");
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				++blockIndex;
			}
		}
		
		public static void printvar(String newCurLine) {
            if (!isInsideForLoop) {
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 30));
                newBlock.setText("Output value of " + newCurLine);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
                newBlock.setBackground(new Color(31, 122, 32));
                bs.blocks.add(newBlock);
                ++blockIndex;
            }
        }
		
		public static void inputB(String TYPE, String VAR_NAME){
            if (!isInsideForLoop){
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 60));
                newBlock.setText("Get a " + TYPE +  " from the user and\n store it to " + VAR_NAME);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
                newBlock.setBackground(new Color(31, 122, 32));
                bs.blocks.add(newBlock);
                blockIndex += 2;
            }
        }

		public static void addB(String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText("Add ");
				for (int i = 0; i < aBunchOfVariables.length; i++) {
					newBlock.setText(newBlock.getText() + aBunchOfVariables[i].toString() + ", ");
					if (i == 3) {
						newBlock.setText(newBlock.getText() + "\n");
					}
				}
				newBlock.setText(newBlock.getText() +"\n together");
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void subB(String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 30));
				newBlock.setText(("Subtract " + aBunchOfVariables[0] + " from " + aBunchOfVariables[1]));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				++blockIndex;
			}
		}

		public static void multiB(String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText("Multiply ");
				for (int i = 0; i < aBunchOfVariables.length; i++) {
					newBlock.setText(newBlock.getText() + aBunchOfVariables[i].toString() + ", ");
					if (i == 3) {
						newBlock.setText(newBlock.getText() +"\n");
					}
				}
				newBlock.setText(newBlock.getText() +"\n together");
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void divB(String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 30));
				newBlock.setText(("Divide " + aBunchOfVariables[0] + " by " + aBunchOfVariables[1]));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				blockIndex++;
			}
		}

		public static void modB(String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 30));
				newBlock.setText(("Get " + aBunchOfVariables[0] + " modulo " + aBunchOfVariables[1]));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
				newBlock.setBackground(new Color(31, 122, 32));
				bs.blocks.add(newBlock);
				blockIndex++;
			}
		}

		public static void StringB(String index1, String index2) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText(("Create a String called " + index1 + "\n and set its value to " + index2));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				newBlock.setBackground(new Color(0, 31, 189));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void intB(String index1, String index2) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText(("Create an integer called " + index1 + "\n and set its value to " + index2));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				newBlock.setBackground(new Color(0, 31, 189));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void longB(String index1, String index2) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText(("Create a big int called " + index1 + "\n and set its value to " + index2));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				newBlock.setBackground(new Color(0, 31, 189));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void doubleB(String index1, String index2) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText(("Create a double called " + index1 + "\n and set its value to " + index2));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				newBlock.setBackground(new Color(0, 31, 189));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void booleanB(String index1, String index2) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText(("Create a boolean called " + index1 + "\n and set its value to " + index2));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				newBlock.setBackground(new Color(0, 31, 189));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void forB(int numOfTimesToRun, String[] aBunchOfVariables) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText("");
				for (int i = 0; i < aBunchOfVariables.length; ++i) {
					newBlock.setText(newBlock.getText() +aBunchOfVariables[i].toString() + ", ");
					if (i == 2) {
						newBlock.setText(newBlock.getText() + "\n");
					}
				}
				newBlock.setText(newBlock.getText() +numOfTimesToRun + " times");
				newBlock.setBackground(new Color(161, 8, 199));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}

		public static void whileB(String whileParam, String[] aBunchOfVariables, String curLine) {
			if (!isInsideForLoop) {
				JButton newBlock = new JButton();
				newBlock.setLocation(new Point((resized75 + 12), (70 + (blockIndex * 30))));
				newBlock.setSize(new Dimension((resized25 - 50), 60));
				newBlock.setText("While " + whileParam + ", run");
				for (int i = 0; i < aBunchOfVariables.length; i++) {
					newBlock.setText(newBlock.getText() +aBunchOfVariables[i].toString() + ", ");
					if (i == 2) {
						newBlock.setText(newBlock.getText() + "\n");
					}
				}
				newBlock.setBackground(new Color(161, 8, 199));
				newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				bs.blocks.add(newBlock);
				blockIndex += 2;
			}
		}
		
		public static void createFileB(String path) {
            if (!isInsideForLoop) {
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 30));
                newBlock.setText("Create a file at " + path);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
                newBlock.setBackground(new Color(0, 31, 189));
                bs.blocks.add(newBlock);
                ++blockIndex;
            }
        }

        public static void createFolderB(String path) {
            if (!isInsideForLoop) {
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 30));
                newBlock.setText("Create a folder at " + path);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
                newBlock.setBackground(new Color(0, 31, 189));
                bs.blocks.add(newBlock);
                ++blockIndex;
            }
        }

        public static void writeFileB(String data, String path) {
            if (!isInsideForLoop) {
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 60));
                newBlock.setText("Write " + data + " to\n" + path);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
                newBlock.setBackground(new Color(0, 31, 189));
                bs.blocks.add(newBlock);
                blockIndex += 2;
            }
        }

        public static void readFileB(String lineN, String path) {
            if (!isInsideForLoop) {
                JButton newBlock = new JButton();
                newBlock.setLocation(new Point((resized75 + 12), (90 + (blockIndex * 30))));
                newBlock.setSize(new Dimension((resized25 - 50), 60));
                newBlock.setText("Read line " + lineN + " from\n" + path);
                newBlock.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
                newBlock.setBackground(new Color(0, 31, 189));
                bs.blocks.add(newBlock);
                blockIndex += 2;
            }
        }
		
	}
}

