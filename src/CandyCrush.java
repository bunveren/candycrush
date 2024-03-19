/*



import java.util.Random;
import java.util.Scanner;

public class CandyCrush {
    public static String[][]board;
    public static String[][]board_temp;
    static String red = Colors.RED + "R" + Colors.RESET;
    static String green = Colors.GREEN + "G" + Colors.RESET;
    static String blue = Colors.BLUE + "B" + Colors.RESET;
    public static int movesLeft=0;
    public static void main(String[]args){
        boolean isMode1 = true;
        Random random = new Random();

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the game!");
        System.out.println("Enter 's' to start the game or 'q' to quit:");
        String a = scan.next();
        if(a.equals("s")){
            //loop body
            if(isMode1){//collect 200 points in 15 moves
                movesLeft=15;
                System.out.println("You have to collect 200 points in 15 moves!");
                System.out.println("Good luck!");
                System.out.println("Enter the size of the matrix:");
                initializeBoard(scan);
                System.out.println("You have 15 moves to reach the goal!");
                printBoard();
                while(movesLeft>0){
                    play(scan);

                }

            }
            else{
                movesLeft=20;
                System.out.println("You have to collect 100 points for each color in 20 moves!");
                System.out.println("Good luck!");
                System.out.println("Enter the size of the matrix:");
                initializeBoard(scan);
                System.out.println("You have 20 moves to reach the goal!");
                printBoard();
                while(movesLeft>0){
                    play(scan);

                }

            }
        }
        else if(a.equals("q")){
            System.out.println("quitting the game as you wish..");
        }
    }

    public static void initializeBoard(Scanner s){
        String row_s = s.next();
        String col_s = s.next();
        int row = Integer.parseInt(row_s);
        int col = Integer.parseInt(col_s);
        board=new String[row][col];
        Random random = new Random();
        int ran = random.nextInt(3); // 0:R 1:G 2:B
        String[]colors_arr=new String[]{"R","G","B"};
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                if(i-2>=0 && board[i-2][j].equals(board[i-1][j])){
                   String samecolor=board[i-2][j];
                   if(samecolor.equals("R")){colors_arr[0]="";}
                   else if(samecolor.equals("G")){colors_arr[1]="";}
                   else {colors_arr[2]="";}

                   boolean snatch = false;
                   while(!snatch){
                       int a = random.nextInt(3);
                       if(colors_arr[a]!=""){
                           snatch=true;
                           board[i][j]=colors_arr[a];
                       }
                   }

                    if(samecolor.equals("R")){colors_arr[0]="R";}
                    else if(samecolor.equals("G")){colors_arr[1]="G";}
                    else {colors_arr[2]="B";}
                }
                else if(j-2>=0 && board[i][j-2].equals(board[i][j-1])){
                    String samecolor=board[i][j-2];
                    if(samecolor.equals("R")){colors_arr[0]="";}
                    else if(samecolor.equals("G")){colors_arr[1]="";}
                    else {colors_arr[2]="";}

                    boolean snatch = false;
                    while(!snatch){
                        int a = random.nextInt(3);
                        if(!colors_arr[a].equals("")){
                            snatch=true;
                            board[i][j]=colors_arr[a];
                        }
                    }

                    if(samecolor.equals("R")){colors_arr[0]="R";}
                    else if(samecolor.equals("G")){colors_arr[1]="G";}
                    else {colors_arr[2]="B";}
                }
                else{ //free to put all colors
                    board[i][j]=colors_arr[random.nextInt(3)];
                }
            }
        }
    }

    public static void printBoard(){
        //int rco = 0, gco=0, bco=0;
        String res = "";
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){

                if(board[i][j].equals("R")){res+="|"+red;}
                else if(board[i][j].equals("G")){res+="|"+green;}
                else if(board[i][j].equals("B")){res+="|"+blue;}
                else res+="|"+" ";
            }
            res+="|\n";
        }
        System.out.println(res);
    }

    public static void play(Scanner s){
        System.out.println("Enter the cell:");
        String row_s = s.next(); String col_s = s.next();
        int row = Integer.parseInt(row_s); int col = Integer.parseInt(col_s);

        System.out.println("Enter the direction:");
        String direction = s.next();
        if(isValidMove(row-1,col-1,direction)){
            makeMove(row-1,col-1,direction);
            movesLeft--;

        }else System.out.println("nonvalid?tf?");
    }

    public static boolean isMakingSense(int row, int col, String direction){
        //fikir swap yapilan boardi olcmek
        board_temp=new String[board.length][board[0].length];
        for(int i = 0; i<board_temp.length; i++){
            for(int j = 0; j<board_temp[0].length; j++){
                board_temp[i][j]=board[i][j]+"";
            }
        }
        //swapping
        switch(direction){
            case "up": {
                String uptodown = board_temp[row - 1][col];
                String downtoup = board_temp[row][col]; //hedef //burda bi sıkıntı var idnex almakta
                board_temp[row - 1][col] = downtoup;
                board_temp[row][col] = uptodown;
                break;
            }
            case "down": {
                String uptodown = board_temp[row][col]; //hedef
                String downtoup = board_temp[row + 1][col]; //burda sorun var
                board_temp[row][col] = downtoup;
                board_temp[row + 1][col] = uptodown;
                break;
            }
            case "left": {
                String lefttoright = board_temp[row][col - 1];
                String righttoleft = board_temp[row][col]; //hedef
                board_temp[row][col] = lefttoright;
                board_temp[row][col - 1] = righttoleft;
                break;
            }
            case "right": {
                String lefttoright = board_temp[row][col]; //hedef
                String righttoleft = board_temp[row][col + 1];
                board_temp[row][col + 1] = lefttoright;
                board_temp[row][col] = righttoleft;
                break;
            }
        }

        for(int i = 0; i<board_temp.length; i++){
            for(int j = 0; j<board_temp[0].length; j++){
                if(i-2>=0 && board_temp[i-2][j].equals(board_temp[i-1][j])
                        && board_temp[i-2][j].equals(board_temp[i][j])){
                    return true; //yatay match
                }

                else if(j-2>=0 && board_temp[i][j-2].equals(board_temp[i][j-1])
                        && board_temp[i][j-2].equals(board_temp[i][j])){
                    return true; //dikey match
                }
            }
        }

        return false;
    }

    public static boolean isValidMove(int row, int col, String direction){
        if(row==1 && col==1){ //sol ust kose
            if(direction.equals("up") || direction.equals("left")) return false;
        }
        else if(row==1 && direction.equals("up")){ //yukarı kenar
            return false;
        }
        else if(row==1 && col==board[0].length){ //sag ust kose
            if(direction.equals("up") || direction.equals("right")) return false;
        }
        else if(row==board.length && col==1){ //sol alt kose
            if(direction.equals("down") || direction.equals("left")) return false;
        }
        else if(row==board.length && direction.equals("down")){ //asagı kenar
            return false;
        }
        else if(row==board.length && col==board[0].length){ //sag alt kose
            if(direction.equals("down") || direction.equals("right")) return false;
        }
        else if(col==1 && direction.equals("left")){ //sol kenar
            return false;
        }
        else if(col==board[0].length && direction.equals("right")){ //sag kenar
            return false;
        }
        else if(!isMakingSense(row,col,direction)){
            return false;
        }

        return true;
    }

    public static void makeMove(int row, int col, String direction) {
        System.out.println("this move seems valid: "+row+" "+col);
        switch (direction) {
            case "up": {
                String uptodown = board[row - 1][col];
                String downtoup = board[row][col]; //hedef
                board[row - 1][col] = downtoup;
                board[row][col] = uptodown;
                break;
            }
            case "down": {
                String uptodown = board[row][col]; //hedef
                String downtoup = board[row + 1][col];
                board[row][col] = downtoup;
                board[row + 1][col] = uptodown;
                break;
            }
            case "left": {
                String lefttoright = board[row][col - 1];
                String righttoleft = board[row][col]; //hedef
                board[row][col] = lefttoright;
                board[row][col - 1] = righttoleft;
                break;
            }
            case "right": {
                String lefttoright = board[row][col]; //hedef
                String righttoleft = board[row][col + 1];
                board[row][col + 1] = lefttoright;
                board[row][col] = righttoleft;
                break;
            }
        }
        System.out.println("Clearing Board:");
        printBoard();
        System.out.println("--------------");
        //uclu matchleri silme kismi
        clearBoard();
        System.out.println("test:clearing board?");
        printBoard();
    }

    public static void clearBoard(){
        boolean oneMatchHandled = false;
        boolean twoMatchSeen = false;
        String[]colors=new String[]{"R","G","B"};
        Random random = new Random();
        for(int i = 0; i<board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(twoMatchSeen) break;
                if(i-2>=0 && board[i-2][j].equals(board[i-1][j])
                        && board[i-2][j].equals(board[i][j])) { //dikey match
                    if(oneMatchHandled){twoMatchSeen=true;break;}
                    if(!oneMatchHandled){
                        System.out.println("match girdi");
                        oneMatchHandled=true;
                        board[i-2][j]=colors[random.nextInt(3)];
                        board[i-1][j]=colors[random.nextInt(3)];
                        board[i][j]=colors[random.nextInt(3)];
                        i=0; j=0;
                        break;
                    }
                }

                else if(j-2>=0 && board[i][j-2].equals(board[i][j-1])
                        && board[i][j-2].equals(board[i][j])) { //yatay match
                    if(oneMatchHandled){twoMatchSeen=true;break;}
                    if(!oneMatchHandled) {
                        System.out.println("match girdi");
                        oneMatchHandled = true;
                        board[i][j-2]=colors[random.nextInt(3)];
                        board[i][j-1]=colors[random.nextInt(3)];
                        board[i][j]=colors[random.nextInt(3)];
                        i=0; j=0;
                        break;
                    }
                }
            }
        }
        printBoard();
        if(twoMatchSeen) {System.out.println("seen another match");clearBoard();}
    }

}


/*


public static void shuffleBoard(){
        String[]board_elements=new String[(board.length)*(board[0].length)];
        int count=0;
        int R_C = 0, G_C = 0, B_C = 0;
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++) {
                if(board[i][j].equals("R")) R_C++;
                else if(board[i][j].equals("G")) G_C++;
                else if(board[i][j].equals("B")) B_C++;


                board_elements[count] = board[i][j];
                board[i][j]="";
                count++;
            }
        }

        int r_c = 0, b_c = 0, g_c = 0;
        boolean r_full=false, g_full=false, b_full=false;
        //board=new String[row][col];
        Random random = new Random();
        int ran = random.nextInt(3); // 0:R 1:G 2:B
        String[]colors_arr=new String[]{"R","G","B"};
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){

                if(i-2>=0 && board[i-2][j].equals(board[i-1][j])){
                    String samecolor=board[i-2][j];
                    if(samecolor.equals("R")){colors_arr[0]="";}
                    else if(samecolor.equals("G")){colors_arr[1]="";}
                    else {colors_arr[2]="";}

                    boolean snatch = false;
                    while(!snatch){
                        int a = random.nextInt(3);
                        if(colors_arr[a]!=""){
                            snatch=true;
                            board[i][j]=colors_arr[a];
                            if(colors_arr[a].equals("R")) r_c++;
                            else if(colors_arr[a].equals("G")) g_c++;
                            else if(colors_arr[a].equals("B")) b_c++;
                        }
                    }
                    if(R_C==r_c && !r_full){colors_arr[0]=""; r_full=true;}
                    if(G_C==g_c && !g_full){colors_arr[1]=""; g_full=true;}
                    if(B_C==b_c && !b_full){colors_arr[2]=""; b_full=true;}
                    if(samecolor.equals("R") && !r_full){colors_arr[0]="R";}
                    else if(samecolor.equals("G") && !r_full){colors_arr[1]="G";}
                    else if(samecolor.equals("B") && !b_full) {colors_arr[2]="B";}
                }
                else if(j-2>=0 && board[i][j-2].equals(board[i][j-1])){
                    String samecolor=board[i][j-2];
                    if(samecolor.equals("R")){colors_arr[0]="";}
                    else if(samecolor.equals("G")){colors_arr[1]="";}
                    else {colors_arr[2]="";}

                    boolean snatch = false;
                    while(!snatch){
                        int a = random.nextInt(3);
                        if(colors_arr[a]!=""){
                            snatch=true;
                            board[i][j]=colors_arr[a];
                            if(colors_arr[a].equals("R")) r_c++;
                            else if(colors_arr[a].equals("G")) g_c++;
                            else if(colors_arr[a].equals("B")) b_c++;
                        }
                    }
                    if(R_C==r_c && !r_full){colors_arr[0]=""; r_full=true;}
                    if(G_C==g_c && !g_full){colors_arr[1]=""; g_full=true;}
                    if(B_C==b_c && !b_full){colors_arr[2]=""; b_full=true;}
                    if(samecolor.equals("R") && !r_full){colors_arr[0]="R";}
                    else if(samecolor.equals("G") && !r_full){colors_arr[1]="G";}
                    else if(samecolor.equals("B") && !b_full) {colors_arr[2]="B";}
                }
                else{ //NOT free to put all colors
                    boolean snatch = false;
                    while(!snatch){
                        int a = random.nextInt(3);
                        if(colors_arr[a]!=""){
                            snatch=true;
                            board[i][j]=colors_arr[a];
                            if(colors_arr[a].equals("R")) r_c++;
                            else if(colors_arr[a].equals("G")) g_c++;
                            else if(colors_arr[a].equals("B")) b_c++;
                            if(R_C==r_c && !r_full){colors_arr[0]=""; r_full=true;}
                            if(G_C==g_c && !g_full){colors_arr[1]=""; g_full=true;}
                            if(B_C==b_c && !b_full){colors_arr[2]=""; b_full=true;}
                        }
                    }
                }

            }
        }


    }




 */

/*


public void makeMove(int row, int col, String direction){

        switch(direction){
            case "up":{
                String uptodown=board[row-1][col];
                String downtoup=board[row][col]; //hedef
                board[row-1][col]=downtoup;
                board[row][col]=uptodown;
            }
            case "down":{
                String uptodown=board[row][col]; //hedef
                String downtoup=board[row+1][col];
                board[row][col]=downtoup;
                board[row+1][col]=uptodown;
            }
            case "left":{
                String lefttoright=board[row][col+1];
                String righttoleft=board[row][col]; //hedef
                board[row][col]=lefttoright;
                board[row][col+1]=righttoleft;
            }
            case "right":{
                String lefttoright=board[row][col]; //hedef
                String righttoleft=board[row][col-1];
                board[row][col-1]=lefttoright;
                board[row][col]=righttoleft;
            }
        }
        System.out.println("Clearing Board:");
        printBoard();
        System.out.println("--------------");
        //uclu matchleri silme kismi
        String[]colors=new String[]{"R","G","B"};
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(i-2>=0 && board[i-2][j].equals(board[i-1][j])
                        && board[i-2][j].equals(board[i][j])){ //dikey match

                    board[i-2][j]=""; board[i-1][j]=""; board[i][j]="";

                    if(i-3>=0){ //tum sutun asagı kayacak

                        for(int k = i-3; k>=0; k--){
                            String todown = board[k][j];
                            board[k+3][j]=todown;
                            board[k][j]="";
                        }
                    }
                    //en ust 3 bos kaldi

                    for(int satir=board.length-1; satir>=0; satir--){ //asagi sag sol bakilacak
                        if(!board[satir][j].equals("")) continue;
                        if(j-2>=0 && board[satir][j-1].equals(board[satir][j-2])){ //X zone
                            if(board[satir][j-1].equals("R")){colors[0]="";}
                            if(board[satir][j-1].equals("G")){colors[1]="";}
                            if(board[satir][j-1].equals("B")){colors[2]="";}
                        }
                        if(j+2<board[0].length && board[satir][j+1].equals(board[satir][j+2])){//Z zone
                            if(board[satir][j+1].equals("R")){colors[0]="";}
                            if(board[satir][j+1].equals("G")){colors[1]="";}
                            if(board[satir][j+1].equals("B")){colors[2]="";}
                        }
                        if(satir+2<=board.length && !board[satir+2][j].equals("")
                                && !board[satir+2][j].equals("") && board[satir+2][j].equals(board[satir+1][j])){ //T zone
                            if(board[satir][j-1].equals("R")){colors[0]="";}
                            if(board[satir][j-1].equals("G")){colors[1]="";}
                            if(board[satir][j-1].equals("B")){colors[2]="";}
                        }
                        //aim: board[satir][j]
                        boolean snatch=false;
                        Random random=new Random();
                        while(!snatch){
                            String a = colors[random.nextInt(3)];
                            if(a!=""){
                                snatch=true;
                                board[satir][j]=a;
                            }
                        }
                        colors=new String[]{"R","G","B"};

                    }

                }//dikey match handling sonu:


                else if(j-2>=0 && board[i][j-2].equals(board[i][j-1])
                        && board[i][j-2].equals(board[i][j])){ //yatay match

                    board[i][j-2]=""; board[i][j-1]=""; board[i][j]="";

                    for(int sutun=j-2; sutun<=j; sutun++){
                        for(int satir=i-1; satir>=0; satir--){
                            board[satir+1][sutun]=board[satir][sutun];
                            board[satir][sutun]="";
                        }
                    }
                    //en ust 3 bos kaldi

                    for(int sutun = j-2; sutun<=j; sutun++){ //asagi sag sol bakilacak
                        //[i][sutun] ve i:0-2 ve sutun-2 sutun+2
                        //aim: board[satir][j]
                        if(board[i+2][sutun].equals("R")) colors[0]="";
                        if(board[i+2][sutun].equals("G")) colors[1]="";
                        if(board[i+2][sutun].equals("B")) colors[2]="";

                        if(board[i+1][sutun].equals("R")) colors[0]="";
                        if(board[i+1][sutun].equals("G")) colors[1]="";
                        if(board[i+1][sutun].equals("B")) colors[2]="";

                        if(sutun-2>=0 && board[i][sutun-2].equals("R")) colors[0]="";
                        if(sutun-2>=0 && board[i][sutun-2].equals("G")) colors[1]="";
                        if(sutun-2>=0 && board[i][sutun-2].equals("B")) colors[2]="";

                        if(sutun-1>=0 && board[i][sutun-1].equals("R")) colors[0]="";
                        if(sutun-1>=0 && board[i][sutun-1].equals("G")) colors[1]="";
                        if(sutun-1>=0 && board[i][sutun-1].equals("B")) colors[2]="";

                        boolean snatch=false;
                        Random random=new Random();
                        colors=new String[]{"R","G","B"};
                        while(!snatch){
                            String a = colors[random.nextInt(3)];
                            if(a!=""){
                                snatch=true;
                                board[sutun][j]=a;
                            }
                        }
                        colors=new String[]{"R","G","B"};

                    }
                }
                //yatay match handling sonu
                colors=new String[]{"R","G","B"};
            }
        }



    }


 */




public class CandyCrush {
}