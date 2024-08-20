// Name- N.N.H.Gamage/Nethmi Gamage
// UoW ID- w19561510
// IIT ID- 20221447

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    ------------PUZZLE SOLVER-----------
                    -----------------Menu---------------
                    1. Display File names
                    2. Enter filename to solve maze
                    3. Run A* Algorithm on benchmark example
                    4. Exit game
                    ------------------------------------
                    """);
            System.out.print("Please choose an option: ");

            try {
                int option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1:
                        displayAvailableFiles();
                        break;
                    case 2:
                        System.out.print("Enter File name: ");
                        String fileName = input.nextLine();
                        runAStarOnFile("../benchmark/" +fileName);
                        break;
                    case 3:
                        runBenchmarkExample();
                        break;

                    case 4:
                        System.out.println("Exiting!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }


        }
    }

    private static void runAStarOnFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            Pointer start = null, finish = null;


            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            if (lines.isEmpty()){
                System.out.println("The file is empty");
                return;
            }

            int rows=lines.size();
            char[][] maze = new char[rows][];

            for (int y = 0; y < lines.size(); y++) {
                line = lines.get(y);
                int cols=line.length();
                maze[y]=new char[cols];
                for (int x = 0; x < cols; x++) {
                    char cell = line.charAt(x);
                    if (cell == 'S') {
                        start = new Pointer(x, y);
                    } else if(cell == 'F') {
                        finish = new Pointer(x, y);
                    }
                    maze[y][x] = cell;

                }
            }

            long startTime = System.nanoTime();
            AStar solver = new AStar(maze, start, finish);
            List<Pointer> path = solver.findPath();
            long endTime = System.nanoTime();

            if (!path.isEmpty()) {
                solver.printPath(path);
            } else {
                System.out.println("No path found.");
            }
            System.out.println("Execution time: " + (endTime - startTime) / 1_000_000 + " ms");
    }catch(FileNotFoundException e){
        System.out.println("File not found exception e " + fileName);
    }catch(IOException e){
        System.out.println("An error occurred when reading the file " + e.getMessage());
    }catch(Exception e)
    {
        System.out.println("An error occurred while processing the file" + e.getMessage());
    }

}




    private static void runBenchmarkExample() {
        System.out.println("------------------------------------------");
        System.out.println("DEMO file benchmark/puzzle_10.txt");
        System.out.println("------------------------------------------");
        runAStarOnFile("../benchmark/puzzle_10.txt");
    }


    private static void displayAvailableFiles() {
        File directory = new File("../benchmark/.");
        File[] files = directory.listFiles();
        if (files != null && files.length > 0) {
            System.out.println("Available Files: ");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else System.out.println("No files found.");
    }

}