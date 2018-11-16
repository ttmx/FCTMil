/**@author Tiago Teles */
/* All functions in Main class that start with "main" have something to do with the main menu, also known as being outside a game, functions whose name starts with
* game are used when inside a game..
*/
import java.util.Scanner;

class Main {
    private static final String SAIR = "sai";
    private static final String SAIRHELP = " - Termina a execucao do programa";

    private static final String JOGAR = "joga";
    private static final String JOGARHELP = " - Simula uma aposta, dando uma chave";

    private static final String AJUDA = "ajuda";
    private static final String AJUDAHELP = " - Mostra os comandos existentes";

    private static final String NOVOJOGO = "novo";
    private static final String NOVOJOGOHELP = " - Novo jogo dando um valor inicial";

    private static final String FIM = "fim";
    private static final String FIMHELP = " - Termina o jogo em curso";

    private static final String THANKSFORBET = "Obrigado pela aposta.";
    private static final String INEXISTENTE = "Comando inexistente.";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FCTMil milObj = new FCTMil();
        mainCommands(scan,milObj);
    }

    //Here we chose what to do with the inserted commands when outside a game
    public static void mainCommands(Scanner scan,FCTMil milObj){
        while(milObj.getMenuState()){
            System.out.print("> ");
            String command = scan.next();
            switch(command.toLowerCase()){
                case SAIR:
                	mainLeave(scan, milObj);
                    break;
                case AJUDA:
                	mainHelp(scan, milObj);
                    break;
                case NOVOJOGO:
                	gameStart(scan, milObj);
                    break;
                default:
                    scan.nextLine();
                    System.out.println(INEXISTENTE);
                    break;
            }
            
        }
    }

    //Here we chose what to do with the inserted commands when inside a game
    public static void gameCommands(Scanner scan, FCTMil milObj){
        while(milObj.getGameState()){
            System.out.print("FCTMILHOES> ");
            String command = scan.next();
            switch(command.toLowerCase()){
                case FIM:
                    scan.nextLine();
                    gameEnd(scan,milObj);
                    break;
                case JOGAR:
                    gamePlay(scan,milObj);
                    scan.nextLine();
                    break;
                case AJUDA:
                    scan.nextLine();
                    gameHelp(scan,milObj);
                    break;
                default:
                    scan.nextLine();
                    System.out.println(INEXISTENTE);
                    break;
            }
        }
    }

    //Funcoes de interface
    //Displays main menu help
    public static void mainHelp(Scanner scan, FCTMil milObj){
        System.out.println(NOVOJOGO + NOVOJOGOHELP);
        System.out.println(SAIR + SAIRHELP);
		System.out.println(AJUDA + AJUDAHELP);
    }
    //Leaves the program
    public static void mainLeave(Scanner scan, FCTMil milObj){
        System.out.println(milObj.bye());
        milObj.falseMenu();
    }
    //Starts game with a certain prizepool
    public static void gameStart(Scanner scan, FCTMil milObj){
        double prizePool = scan.nextDouble();
        if (prizePool > 0) {
        	milObj.newGame(prizePool);
        	System.out.printf("Jogo iniciado. Valor do premio: %.2f Euros.\n", milObj.getprizepool());
        } else {
            System.out.println("Valor incorrecto.");
		} 
        gameCommands(scan,milObj);
    }
    //Ends the game and enters the main menu
    public static void gameEnd(Scanner scan, FCTMil milObj){
        milObj.falseGame();
        System.out.print(milObj.exit());
    }
    
    //Places a bet with a certain key
    public static void gamePlay(Scanner scan, FCTMil milObj){
        int[] bets = new int[7];
        for(int i = 0; i<7; i++) {
            bets[i] = scan.nextInt();
        }
        int prizeLevel = milObj.bet(bets);
        if (prizeLevel > 0) {
            System.out.println(THANKSFORBET +" Premio de nivel: "+prizeLevel);
        } else if(prizeLevel == -1) {
            System.out.println("Chave incorrecta.");
        } else {
            System.out.println(THANKSFORBET);
        }
    }
    //Help command inside the game.
    public static void gameHelp(Scanner scan, FCTMil milObj){
        System.out.println(JOGAR + JOGARHELP);
        System.out.println(FIM + FIMHELP);
        System.out.println(AJUDA + AJUDAHELP);
    }
}