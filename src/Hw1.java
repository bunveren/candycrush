import java.util.Random;
import java.util.Scanner;
//BEREN UNVEREN 221101006
public class Hw1 {
    public static String[][]board;
    public static String[][]board_temp;
    static String red = Colors.RED + "R" + Colors.RESET;
    static String green = Colors.GREEN + "G" + Colors.RESET;
    static String blue = Colors.BLUE + "B" + Colors.RESET;
    public static int movesLeft=0;
    public static int overallscore=0;
    public static int redscore=0, greenscore=0, bluescore=0;
    public static boolean isMode1=true;

    public static boolean isInShuffle=false;

    //play_game metodunu cagirmayla basliyor ve play_game icinde input aliyor. s girilmezse oyundan cikiyor.
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        System.out.println("Enter 's' to start the game or 'q' to quit:");
        String a = scan.next();
        if(a.equals("s")){
            play_game(scan);
        }
        else{
            System.out.println("quitting the game as you wish..");
        }
    }



    /*
    modu randomdan int alarak karar verdiriyorum.
    initialize boardi cagirip match olusmayacak sekilde boardi olusturuyorum.
    her adimda input metoduyla input aliyorum (input metodu icinde inputu alip bu hareket gecerli mi diye bakan
    isvalidi cagiriyor, sonra da isvalid metodu bu hareket match yaratiyor mu diye
    bakan ismakingsense i cagiriyor. eger validse makemove cagriliyor ve move sayisi azaltiliyor)
    sonrasinda shuffle icin kontrol edici metodu cagiriyorum.

    oyun bitisini de input alarak karar veriyorum ve booleani inputa gore bagliyorum.
     */
    public static void play_game(Scanner scan){
        Random random;
        boolean playing=true;
        while(playing){
            isMode1 = true;
            random = new Random();
            if(random.nextInt(2)==0) isMode1=false;
            overallscore=0; redscore=0; greenscore=0;

            if(isMode1){//collect 200 points in 15 moves
                movesLeft=15;
                System.out.println("You have to collect 200 points in 15 moves!");
                System.out.println("Good luck!");
                System.out.println("Enter the size of the matrix:");
                initializeBoard(scan);
                System.out.println("You have 15 moves to reach the goal!");
                printBoard();
                while(movesLeft>0 && overallscore<200){
                    take_input(scan);
                    callForShuffle();
                    System.out.println("You have collected "+overallscore+" points and you have " +
                            movesLeft+" moves left!");
                }
                if(movesLeft>=0 && overallscore>=200){
                    System.out.println("You won the game! Wanna play again?");
                    System.out.println("Enter 's' to start the game or 'q' to quit:");
                    String a = scan.next();
                    if(a.equals("s")) playing=true;
                    else{
                        playing=false;
                        System.out.println("quitting the game as you wish..");
                    }
                }
                else if(movesLeft==0 && overallscore<200){
                    System.out.println("You lost the game! Wanna play again?");
                    System.out.println("Enter 's' to start the game or 'q' to quit:");
                    String a = scan.next();
                    if(a.equals("s")) playing=true;
                    else{
                        playing=false;
                        System.out.println("quitting the game as you wish..");
                    }
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
                while(movesLeft>0 && redscore<100 && greenscore<100 && bluescore<100){
                    take_input(scan);
                    callForShuffle();
                    System.out.println("You have collected "+
                            redscore+" points for red, "+
                            greenscore+" points for green, "+
                            bluescore+" points for blue"+
                            " and you have " +
                            movesLeft+" moves left!");
                }
                if(movesLeft>=0 && redscore>=100 && greenscore>=100 && bluescore>=100){
                    System.out.println("You won the game! Wanna play again? ");
                    System.out.println("Enter 's' to start the game or 'q' to quit:");
                    String a = scan.next();
                    if(a.equals("s")) playing=true;
                    else{
                        playing=false;
                        System.out.println("quitting the game as you wish..");
                    }
                }
                else if(movesLeft==0 && (redscore<100 || greenscore<100 || bluescore<100)){
                    System.out.println("You lost the game! Wanna play again?");
                    System.out.println("Enter 's' to start the game or 'q' to quit:");
                    String a = scan.next();
                    if(a.equals("s")) playing=true;
                    else{
                        playing=false;
                        System.out.println("quitting the game as you wish..");
                    }
                }
            }
        }
    }



    /*
    for loop icinde her eleman koyusta cevredeki elemanlara bakip bu elemanlarla match olusturuyor mu bakiyorum eger belirli renkte
    olusturuyorsa onu gecici olarak colors arrayinden cikarip onun secilememesini sagliyorum.
     */
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



    /*
    inputu alip bu hareket gecerli mi diye bakan isvalidi cagiriyor, sonra da isvalid metodu bu hareket match yaratiyor mu diye
    bakan ismakingsense i cagiriyor. eger validse makemove cagriliyor ve move sayisi azaltiliyor.
     */
    public static void take_input(Scanner s){
        System.out.println("Enter the cell:");
        String row_s = s.next(); String col_s = s.next();
        int row = Integer.parseInt(row_s); int col = Integer.parseInt(col_s);

        System.out.println("Enter the direction:");
        String direction = s.next();
        if(isValidMove(row,col,direction)){
            makeMove(row-1,col-1,direction);
            movesLeft--;
        }
        else System.out.println("you tried to make a nonvalid move");
    }



    /*
    burada invalid olacak tum durumlari dusunup onlari olcmeye calistim. en sonda da
    ismakingsense metodunu cagiriyorum cunku move legal olsa da match yaratmasi da gerekiyor
     */
    public static boolean isValidMove(int row, int col, String direction){

        if(row==1 && col==1){ //sol ust kose
            if(direction.equals("up") || direction.equals("left")) {
                //System.out.println("r1c1 up-left"); //test for isvalid
                return false;
            }
        }
        if(row==1 && col==board[0].length){ //sag ust kose
            if(direction.equals("up") || direction.equals("right")) {
                //System.out.println("r1cson up-right"); //test for isvalid
                return false;
            }
        }
        if(row==1 && direction.equals("up")){ //yukarı kenar
            //System.out.println("r1 up"); //test for isvalid
            return false;
        }
        if(row==board.length && col==1){ //sol alt kose
            if(direction.equals("down") || direction.equals("left")) {
                //System.out.println("rsonc1 down-left"); //test for isvalid
                return false;
            }
        }
        if(row==board.length && col==board[0].length){ //sag alt kose
            if(direction.equals("down") || direction.equals("right")) {
                //System.out.println("rsoncson down-right"); //test for isvalid
                return false;
            }
        }
        if(row==board.length && direction.equals("down")){ //asagı kenar
            //System.out.println("rson down"); //test for isvalid
            return false;
        }
        if(col==1 && direction.equals("left")){ //sol kenar
            //System.out.println("col1 left"); //test for isvalid
            return false;
        }
        if(col==board[0].length && direction.equals("right")){ //sag kenar
            //System.out.println("cson right"); //test for isvalid
            return false;
        }
        if(!isMakingSense(row-1,col-1,direction)){
            //System.out.println("not making sense");
            return false;
        }
        //System.out.println(row+" "+col+" "+direction+" passed all valid checks");
        return true;
    }



    /*
    verilen konum ve hareketi temp boardda gerceklestirip match yaratiyor mu diye kontrol ediyorum
     */
    public static boolean isMakingSense(int row, int col, String direction){

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
                String downtoup = board_temp[row][col];
                board_temp[row - 1][col] = downtoup;
                board_temp[row][col] = uptodown;
                break;
            }
            case "down": {
                String uptodown = board_temp[row][col];
                String downtoup = board_temp[row + 1][col];
                board_temp[row][col] = downtoup;
                board_temp[row + 1][col] = uptodown;
                break;
            }
            case "left": {
                String lefttoright = board_temp[row][col - 1];
                String righttoleft = board_temp[row][col];
                board_temp[row][col] = lefttoright;
                board_temp[row][col - 1] = righttoleft;
                break;
            }
            case "right": {
                String lefttoright = board_temp[row][col];
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



    /*
    burada inputa gore bir yandaki tasla degisim hareketini gerceklestirip,
    boardi basip, en son matchleri silecek olan clearboard metodunu cagiriyorum.
    normalde bir kere cagirinca oluyordu bir anda sikinti cikti ben de clearboardi double check icin iki kere
    cagirdim bu yuzden cogu zaman en son olusan boardi iki kere basiyor sanirim
     */
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
        clearBoard();
        clearBoard(); //sacma bir hatadan dolayi ekledim ve bu sekilde sorunsuz calisiyor

    }



    /*
    buradaki booleanlarin amaci forloop icinde bir match gordugunde onu gerceklestirmesi,
    ikinci matchi gorurse basa donup ilk matchmis gibi degerlendirmesi ve ikinci matchi tekrar aramasi.
    yani en sonuncu iterasyonda ikinci matchi gormeyecek(baska match kalmamis olacak) ve bu sekilde looptan
    cikabilecek.
    skor vermeyi de burada ayarladim ve shuffle boarddan sonra clear yaparken puan eklenmesin diye boolean tuttum
     */
    public static void clearBoard(){
        while(true){
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
                            System.out.println("seen match");
                            oneMatchHandled=true;
                            if(isMode1 && !isInShuffle){
                                overallscore+=10;
                            }
                            else if(!isInShuffle){
                                switch (board[i][j]){
                                    case "R":{redscore+=5;break;}
                                    case "G":{greenscore+=5;break;}
                                    case "B":{bluescore+=5;break;}
                                }
                            }

                            board[i-2][j]=colors[random.nextInt(3)];
                            board[i-1][j]=colors[random.nextInt(3)];
                            board[i][j]=colors[random.nextInt(3)];
                            printBoard();
                            i=0; j=0;
                            break;
                        }
                    }

                    else if(j-2>=0 && board[i][j-2].equals(board[i][j-1])
                            && board[i][j-2].equals(board[i][j])) { //yatay match
                        if(oneMatchHandled){twoMatchSeen=true;break;}
                        if(!oneMatchHandled) {
                            System.out.println("seen match");
                            oneMatchHandled = true;
                            if(isMode1 && !isInShuffle){
                                overallscore+=10;
                            }
                            else if(!isInShuffle){
                                switch (board[i][j]){
                                    case "R":{redscore+=5;break;}
                                    case "G":{greenscore+=5;break;}
                                    case "B":{bluescore+=5;break;}
                                }
                            }
                            board[i][j-2]=colors[random.nextInt(3)];
                            board[i][j-1]=colors[random.nextInt(3)];
                            board[i][j]=colors[random.nextInt(3)];
                            printBoard();
                            i=0; j=0;
                            break;
                        }
                    }
                }
            }

            if(twoMatchSeen) {System.out.println("another");}
            else{
                //System.out.println("no more matches:");
                //printBoard();
                break;
            }
        }
    }



    /*
    shufflecall metodu icin yardimci kucuk bir metod
     */
    public static boolean inBorder(int row, int col){
        return (row>=0 && row<board.length)&&(col>=0 && col<board[0].length);
    }



    /*
    bunu her movedan sonra cagiriyorum ki hicbir match yapabilecek move olmadigini anladiginda shufflei cagirsin
    16 farkli durumu inceliyorum bir tane bile match durumu yaratilabiliyorsa looptan cikacak
    match olabilmesini de tas renklerini karsilastirarak kontrol ettim
     */
    public static void callForShuffle(){
        boolean over = false;
        while(!over){
            boolean movePossible = false;
            for(int i = 0; i<board.length; i++){
                for(int j = 0; j<board[0].length; j++){
                    String aim = board[i][j];

                    //horizontal_edge check
                    if(    ((inBorder(i-1,j-2) && inBorder(i-1,j-1)) && board[i-1][j-2].equals(board[i-1][j-1]) && board[i-1][j-2].equals(aim))
                            || ((inBorder(i-1,j+2) && inBorder(i-1,j+1)) && board[i-1][j+2].equals(board[i-1][j+1]) && board[i-1][j+2].equals(aim))
                            || ((inBorder(i+1,j-2) && inBorder(i+1,j-1)) && board[i+1][j-2].equals(board[i+1][j-1]) && board[i+1][j-2].equals(aim))
                            || ((inBorder(i+1,j+2) && inBorder(i+1,j+1)) && board[i+1][j+2].equals(board[i+1][j+1]) && board[i+1][j+2].equals(aim))
                    ) {movePossible=true;}

                    //vertical_edge check
                    else if(   ((inBorder(i-2,j-1) && inBorder(i-1,j-1)) && board[i-2][j-1].equals(board[i-1][j-1]) && board[i-2][j-1].equals(aim))
                            || ((inBorder(i-2,j+1) && inBorder(i-1,j+1)) && board[i-2][j+1].equals(board[i-1][j+1]) && board[i-2][j+1].equals(aim))
                            || ((inBorder(i+1,j-1) && inBorder(i+2,j-1)) && board[i+1][j-1].equals(board[i+2][j-1]) && board[i+1][j-1].equals(aim))
                            || ((inBorder(i+1,j+1) && inBorder(i+2,j+1)) && board[i+1][j+1].equals(board[i+2][j+1]) && board[i+1][j+1].equals(aim))
                    ) {movePossible=true;}

                    //at_middle check
                    else if(   ((inBorder(i-1,j-1) && inBorder(i+1,j-1)) && board[i-1][j-1].equals(board[i+1][j-1]) && board[i-1][j-1].equals(aim))
                            || ((inBorder(i-1,j-1) && inBorder(i-1,j+1)) && board[i-1][j-1].equals(board[i-1][j+1]) && board[i-1][j-1].equals(aim))
                            || ((inBorder(i-1,j+1) && inBorder(i+1,j+1)) && board[i-1][j+1].equals(board[i+1][j+1]) && board[i+1][j+1].equals(aim))
                            || ((inBorder(i+1,j-1) && inBorder(i+1,j+1)) && board[i+1][j-1].equals(board[i+1][j+1]) && board[i+1][j+1].equals(aim))
                    ) {movePossible=true;}

                    //blank_edge check
                    else if(   ((inBorder(i,j-3) && inBorder(i,j-2)) && board[i][j-3].equals(board[i][j-2]) && board[i][j-3].equals(aim))
                            || ((inBorder(i,j+3) && inBorder(i,j+2)) && board[i][j+3].equals(board[i][j+2]) && board[i][j+3].equals(aim))
                            || ((inBorder(i-3,j) && inBorder(i-2,j)) && board[i-3][j].equals(board[i-2][j]) && board[i-3][j].equals(aim))
                            || ((inBorder(i+3,j) && inBorder(i+2,j)) && board[i+3][j].equals(board[i+2][j]) && board[i+3][j].equals(aim))
                    ) {movePossible=true;}

                    //break
                    if(movePossible) {over=true; break;}
                }
                if(movePossible) {over=true; break;}
            }

            if(!movePossible) {
                System.out.println("no possible moves, shuffling the board:");
                shuffleBoard();
                //printBoard();
                //shuffle edilen boarddaki matchlerin silinmesi gerekiyor
                isInShuffle=true;
                clearBoard();
                isInShuffle=false;
            }
        }
    }



    /*
    tas renklerinin sayisini tutuyor boardi bosaltiyor ve tekrar dolduruyor (sayiya gore ve randomize bir sekilde)
     */
    public static void shuffleBoard(){ //aynı boarda yazılacak
        int r_count=0, g_count=0, b_count=0;
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j].equals("R")) r_count++;
                if(board[i][j].equals("G")) g_count++;
                if(board[i][j].equals("B")) b_count++;
                board[i][j]="";
            }
        }
        Random random = new Random();
        String[]colors=new String[]{"R","G","B"};
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                int a = random.nextInt(3);
                boolean snatch = false;
                while(!snatch){
                    if(a==0 && r_count>0){board[i][j]=colors[a]; r_count--; snatch=true;}
                    if(a==1 && g_count>0){board[i][j]=colors[a]; g_count--; snatch=true;}
                    if(a==2 && b_count>0){board[i][j]=colors[a]; b_count--; snatch=true;}
                    if(!snatch) a = random.nextInt(3);
                }
            }
        }
    }



    /*
    verilen colors classini kullandim ve string arrayi kullandigim icin gelen stringe gore color variableini
    basilacak stringe ekliyorum
     */
    public static void printBoard(){
        String res = "";
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){

                if(board[i][j].equals("R")){res+="|"+red;}
                else if(board[i][j].equals("G")){res+="|"+green;}
                else if(board[i][j].equals("B")){res+="|"+blue;}
                else res+="|"+" "; //buraya gelmiyor normal sartlarda
            }
            res+="|\n";
        }
        System.out.println(res);
    }
}