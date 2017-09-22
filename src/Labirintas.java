import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labirintas {
    //static String filename = "file1.txt";
    //static String filename = "file2.txt";
    static String filename = "file3.txt";
    static int[][] LAB;
    static ArrayList<Integer> tempList = new ArrayList<Integer>();
    static ArrayList<Integer> keliasT = new ArrayList<Integer>();
    static ArrayList<Integer> keliasVX = new ArrayList<Integer>();   // kelias virsunemis X
    static ArrayList<Integer> keliasVY = new ArrayList<Integer>();   // kelias virsunemis Y
    static int[] CX = new int[4 + 1]; static int[] CY = new int[4 + 1];
    static int L = 2;
    static int X;
    static int Y;
    static int BANDSK;
    static boolean YRA;
    static Scanner scanner = new Scanner(System.in);
    static int counter = 0;
    static int rCounter = 0;
    static int pointCounter = 0;
    static int lineCounter = 0;
    static int numberCounter = 0;

    public static void eiti(int X, int Y){
        int K = 0;
        int U = 0;
        int V = 0;

        if ((X == 1) || (X == numberCounter) || (Y == 1) || (Y == lineCounter)){
            YRA = true;
            System.out.print(" Terminal.");
            //output.write(" Terminal.");
        } else{
            K = 0;
            do{
                K = K + 1;
                U = X + CX[K]; V = Y + CY[K];

                System.out.println();
                //output.write('\n');
                System.out.print(String.format("%8s", ++counter) + ") ");
                //--counter;
                //output.write(String.format("%8s", ++counter) + ") ");

                for (int i = 0; i < pointCounter; i++) {
                    System.out.print(".");
                    //output.write(".");
                }
                System.out.print("R" + K + ". U=" + U + ", V=" + V + ". ");
                //output.write("R" + K + ". U=" + U + ", V=" + V + ". ");
                if (LAB[U][V] == 0){
                    rCounter++;
                    System.out.print(" Laisva.");
                    //output.write(" Laisva.");
                    System.out.print(" L:=L+1=" + (L + 1) + ".");
                    //output.write(" L:=L+1=" + (L + 1) + ".");
                    L = L + 1;
                    LAB[U][V] = L;
                    System.out.print(" LAB[" + U + "," + V + "] := " + L + ".");
                    //output.write(" LAB[" + U + "," + V + "] := " + L + ".");
                    ++pointCounter;
                    BANDSK = BANDSK + 1;
                    eiti(U, V);
                    if(!YRA){
                        System.out.println();
                        System.out.print("          ");
                        for (int i = 0; i < pointCounter; i++) {
                            System.out.print(".");
                            //output.write(".");
                        }
                        --pointCounter;
                        LAB[U][V] = -1;
                        System.out.print("Backtrack iš X=" + U + ", Y=" + V + ", " +
                            "L=" + L + ". LAB[" + U + "," + V + "]:=" + LAB[U][V] + ". L:=L-1=" + (L - 1) + ".");
                        //output.write("Backtrack iš X=" + X + ", Y=" + Y + ",
                        // L=" + L + ". LAB[" + X + "," + Y + "]:=" + LAB[X][Y] + ". L:=L-1=" + (L - 1) + ".");
                        L = L - 1;
                    }else{
                        keliasT.add(0, K);
                        keliasVX.add(0, U);
                        keliasVY.add(0, V);
                    }
                } else if (LAB[U][V] == 1){
                    System.out.print(" Siena.");
                    //output.write("  Siena.");
                } else{
                    System.out.print(" Siūlas.");
                    //output.write("  Siūlas.");
                }
            } while(!(YRA || (K == 4)));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int charCounter;
        String character;

        try {
            while ((line = br.readLine()) != null) {
                charCounter = 0;
                numberCounter = 1;
                lineCounter++;
                while (line.length() > charCounter){
                    character = String.valueOf(line.charAt(charCounter));
                    if (!character.equals(" ") && !character.equals('\n')){
                        tempList.add(Integer.parseInt(character));
                        numberCounter++;
                    }
                    charCounter++;
                }
            }
        } finally {
            br.close();
        }

        int index = 0;
        LAB = new int[numberCounter + 1][lineCounter + 1];
        for (int i = lineCounter; i >= 1; i--){
            for (int j = 1; j < numberCounter; j++){
                LAB[j][i] = tempList.get(index);
                index++;
            }
        }

        System.out.println("Įveskite pradinę agento padėtį (x, y) ");
        X = scanner.nextInt();
        Y = scanner.nextInt();

        CX[1] = -1; CY[1] = 0;
        CX[2] =  0; CY[2] = -1;
        CX[3] =  1; CY[3] = 0;
        CX[4] =  0; CY[4] = 1;

        System.out.println("1 DALIS. Duomenys");
        System.out.println("  1.1. Labirintas");
        System.out.println();
        /*output.write("1 DALIS. Duomenys ");
        output.write('\n');
        output.write("  1.1. Labirintas");
        output.write('\n');
        output.write('\n');*/
        printLabirith();
        System.out.println("  1.2. Pradinė padėtis X=" + X + ", Y=" + Y + ". L=2.");
        System.out.println();
        /*output.write('\n');
        output.write('\n');
        output.write("  1.2. Pradinė padėtis X=" + X + ", Y=" + Y + ". L=2.");
        output.write('\n');
        output.write('\n');*/
        System.out.println("2 DALIS. Vykdymas");
        /*output.write("2 DALIS. Vykdymas");
        output.write('\n');*/

        YRA = false; BANDSK = 0;
        LAB[X][Y] = 2;
        long startTime = System.currentTimeMillis();
        eiti(X, Y);
        keliasVX.add(0, X);
        keliasVY.add(0, Y);

        System.out.println();
        System.out.println("3 DALIS. Rezultatai");
        System.out.println();
        /*output.write('\n')
        output.write("3 DALIS. Rezultatai");
        output.write('\n')
        output.write('\n');*/
        if (YRA){
            System.out.println("  3.1. Kelias rastas.");
            System.out.println();
            //output.write("  3.1. Kelias rastas.");
            //output.write('\n');
            //output.write('\n');
            //output.write("   Y, V ^");
            //output.write('\n');
            System.out.println("  3.2. Kelias grafiškai");
            System.out.println();
            //output.write("  3.2. Kelias grafiškai");
            //output.write('\n');
            //output.write('\n');
            printLabirith();
            System.out.print("  3.3. Kelias taisyklėmis: ");
            for (int i = 0; i < keliasT.size(); i++){
                if (i != keliasT.size() - 1){
                    System.out.print("R" + keliasT.get(i) + ", ");
                }else{
                    System.out.print("R" + keliasT.get(i) + ".");
                }
            }
            System.out.println();
            //output.write("  3.3. Kelias taisyklėmis: ");
            //output.write('\n');
            //output.write('\n');
            System.out.print("  3.4. Kelias viršūnėmis: ");
            for (int i = 0; i < keliasVX.size(); i++){
                if (i != keliasVX.size() - 1) {
                    System.out.print("[X=" + keliasVX.get(i) + ", " + "Y=" + keliasVY.get(i) + "], ");
                }else{
                    System.out.print("[X=" + keliasVX.get(i) + ", " + "Y=" + keliasVY.get(i) + "].");
                }
            }
            System.out.println();
            //output.write("  3.4. Kelias viršūnėmis: ");
            //output.write('\n');
            //output.write('\n');
        } else{
            System.out.print("  3.1. Kelias nerastas.");
            //output.write("  3.1. Kelias nerastas.");
        }
        /*
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        System.out.print("   Vykdymo laikas " + totalTime + "s");
        //output.write("   Vykdymo laikas " + totalTime + "s");
        */
    }

    public static void printLabirith(){
        System.out.println("   Y, V ^");
        //output.write("   Y, V ^");
        //output.write('\n');

        for (int i = lineCounter; i > 0; i--) {
            System.out.print(String.format("%7s", + i) + " | ");
            //output.write(String.format("%7s", + i) + " | ");
            for (int j = 1; j < numberCounter; j++) {
                System.out.print(String.format("%2s", LAB[j][i]) + "  ");
                //output.write(String.format("%2s", LAB[j][i]) + "  ");
            }
            System.out.println();
            //output.write('\n');
        }
        System.out.print("        ");
        //output.write("        ");
        for (int i = 0; i < numberCounter; i++){
            System.out.print("-----");
            //output.write("-----");
        }
        System.out.println("> X, U");
        // output.write("> X, U");
        //output.write('\n');
        System.out.print("          ");
        //output.write("          ");
        for (int j = 1; j < numberCounter; j++) {
            System.out.print(String.format("%2s", j) + "  ");
            //output.write(String.format("%2s", j) + "  ");
        }
        System.out.println();
        System.out.println();
    }
}
