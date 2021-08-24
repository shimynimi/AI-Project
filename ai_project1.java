
import java.util.*;

public class ai_project1 {

    public static void main(String[] args) {
        vacuum_cleaner VC = new vacuum_cleaner();
        VC.initialize();
    }

}

class vacuum_cleaner {

    //actions = 1,2,3,4 (right, left, up, down)
    int action;
    int row;
    int col;
    int n;
    int m;
    int[][] environment;
    int[][] StatusEnvironment;
    int cnt;
    int flag;
    int strt;
    int dir;
    int mode = 0;

    public void initialize() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("enter number of ROWS (more than 80) : ");
            n = input.nextInt();
            if (n < 80) {
                System.out.println("!!! more than 80 !!!TRY AGAIN");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println("enter number of COLUMNS (more than 80) : ");
            m = input.nextInt();
            if (m < 80) {
                System.out.println("!!! more than 80 !!!TRY AGAIN");
            } else {
                break;
            }
        }

        environment = new int[n][m];
        StatusEnvironment = new int[n][m];
        Random Rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                environment[i][j] = Rand.nextInt(2);
                StatusEnvironment[i][j] = 0;
            }
        }

        System.out.println("input start row number: ");
        row = input.nextInt();
        System.out.println("input start colmun number: ");
        col = input.nextInt();

        cnt = 0;
        flag = 0;
        strt = 0;
        int[] percept = new int[3];
        percept[0] = row;
        percept[1] = col;

        while (true) {
            System.out.println("---------------------------------");
            System.out.println("round: " + cnt);
            System.out.println("loc: " + row + " " + col);
            action = agent(percept);
            percept = env(action);
            System.out.println("action:" + action);
            System.out.println("percept: " + "[" + percept[0] + "," + percept[1] + "," + percept[2] + "]");
            System.out.println("main map");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(environment[i][j] + " ");
                }
                System.out.println("");
            }

            System.out.println("visit map");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(StatusEnvironment[i][j] + " ");
                }
                System.out.println("");
            }
            cnt += 1;
            if (flag == 1) {
                System.out.println("--------------------------\nfinished in " + cnt + " round!");
                break;
            }

        }

    }

    public int agent(int[] percept) {

        int row = percept[0];
        int col = percept[1];

        //If it is dirty, clean it
        if (percept[2] == 1) {
            environment[row][col] = 0;
        }

        //This cell has been seen
        StatusEnvironment[row][col] = 1;

        int unvisited_cells = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (StatusEnvironment[i][j] == 0) {
                    unvisited_cells = 1;
                }
            }
        }

        if (unvisited_cells == 0) {
            System.out.println("no unvisited cells!");
            flag = 1;
            action = 0;
        } else {

            //First round
            if (cnt == 0) {
                if (row < (n / 2) && col < (m / 2)) {
                    mode = 1;
                } else if (row < (n / 2) && col >= (m / 2)) {

                    mode = 2;
                } else if (row >= (n / 2) && col < (m / 2)) {
                    mode = 3;
                } else if (row >= (n / 2) && col >= (m / 2)) {
                    mode = 4;
                }
                System.out.println("mode: " + mode);
            }

            //Other rounds
            if (mode == 1) {

                if (row == 0 && col == 0) {
                    strt = 1;
                    dir = 1;
                    System.out.println("started");
                }

                if (strt == 0) {
                    if (col > 0) {
                        action = 2;
                    } else if (row > 0) {
                        action = 3;
                    }
                }

                if (strt == 1) {
                    if (dir == 1) {
                        if (col < m - 1) {
                            action = 1;
                        } else if (col == m - 1) {
                            action = 4;
                            dir = 2;
                        }
                    } else if (dir == 2) {
                        if (col > 0) {
                            action = 2;
                        } else {
                            action = 4;
                            dir = 1;
                        }
                    }
                }
            }
            ///////////////////////////////////////////
            if (mode == 2) {

                if (row == 0 && col == m - 1) {
                    strt = 1;
                    dir = 2;
                    System.out.println("started");
                }

                if (strt == 0) {
                    if (col < m - 1) {
                        action = 1;
                    } else if (row > 0) {
                        action = 3;
                    }
                }

                if (strt == 1) {
                    if (dir == 1) {
                        if (col < m - 1) {
                            action = 1;
                        } else {
                            action = 4;
                            dir = 2;
                        }
                    } else if (dir == 2) {
                        if (col > 0) {
                            action = 2;
                        } else {
                            action = 4;
                            dir = 1;
                        }
                    }

                }

            }
            ///////////////////////////////////////////
            if (mode == 3) {

                if (row == n - 1 && col == 0) {
                    strt = 1;
                    dir = 1;
                    System.out.println("started");
                }

                if (strt == 0) {
                    if (col > 0) {
                        action = 2;
                    } else if (row < n - 1) {
                        action = 4;
                    }
                }

                if (strt == 1) {
                    if (dir == 1) {
                        if (col < m - 1) {
                            action = 1;
                        } else {
                            action = 3;
                            dir = 2;
                        }
                    } else if (dir == 2) {
                        if (col > 0) {
                            action = 2;
                        } else {
                            action = 3;
                            dir = 1;
                        }
                    }
                }
            }
            ///////////////////////////////////////////
            if (mode == 4) {

                if (row == n - 1 && col == m - 1) {
                    strt = 1;
                    dir = 2;
                    System.out.println("started");
                }

                if (strt == 0) {
                    if (col < m - 1) {
                        action = 1;
                    } else if (row < n - 1) {
                        action = 4;
                    }
                }

                if (strt == 1) {
                    if (dir == 1) {
                        if (col < m - 1) {
                            action = 1;
                        } else {
                            action = 3;
                            dir = 2;
                        }
                    } else if (dir == 2) {
                        if (col > 0) {
                            action = 2;
                        } else {
                            action = 3;
                            dir = 1;
                        }
                    }
                }
            }
        }

        return action;
    }

    public int[] env(int action) {
        int[] percept = new int[3];

        if (action == 0) {
            flag = 1;
            percept[0] = -1;
            percept[1] = -1;
            percept[2] = -1;

        } else if (action == 1) {
            col += 1;
            if (environment[row][col] == 1) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 1;
            } else if (environment[row][col] == 0) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 0;

            }

        } else if (action == 2) {
            col -= 1;

            if (environment[row][col] == 1) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 1;
            } else if (environment[row][col] == 0) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 0;
            }
        } else if (action == 3) {
            row -= 1;
            if (environment[row][col] == 1) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 1;
            } else if (environment[row][col] == 0) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 0;
            }

        } else if (action == 4) {
            row += 1;
            if (environment[row][col] == 1) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 1;
            } else if (environment[row][col] == 0) {
                percept[0] = row;
                percept[1] = col;
                percept[2] = 0;
            }

        }

        return percept;
    }

}
