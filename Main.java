/**@author Tiago Teles */
/* Todas as funcoes com main no inicio do nome referem-se ao que acontece antes de haver um jogo iniciado,
*  as com game no inicio serao entao as ja dentro do jogo.
*/
import java.util.Scanner;
class Main{
    private static final String SAIR = "sai";
    private static final String JOGAR = "joga";
    private static final String AJUDA = "ajuda";
    private static final String NOVOJOGO = "novo";
    private static final String FIM = "fim";
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FCTMil milObj = new FCTMil();
        mainCommands(scan,milObj);
    }
    //Aqui escolhemos o que fazer com os comandos inseridos na consola quando fora do um jogo
    public static void mainCommands(Scanner scan,FCTMil milObj){
        while(milObj.inMenu){
            System.out.print("> ");
            String command = scan.next();
            switch(command.toLowerCase()){
                case SAIR:mainLeave(scan, milObj); 
                    break;
                case AJUDA:mainHelp(scan, milObj);
                    break;
                case NOVOJOGO:gameStart(scan, milObj);
                    break;
                default: 
                    System.out.println("Comando inexistente.");
                    break;
            }
        }
    }
    //Aqui escolhemos o que fazer com os comandos inseridos na consola quando fora do um jogo
    public static void gameCommands(Scanner scan, FCTMil milObj){
        while(milObj.inGame){
            System.out.print("FCTMILHOES> ");
            String command = scan.next();
            switch(command.toLowerCase()){
                case FIM:
                    gameEnd(scan,milObj);
                    break;
                case JOGAR:
                    gamePlay(scan,milObj);
                    break;
                case AJUDA:
                    gameHelp(scan,milObj);
                    break;
                default:
                    System.out.println("Comando inexistente.");
                    break;
            }
        }
    }
    //Funcoes de interface

    public static void mainHelp(Scanner scan, FCTMil milObj){
        System.out.println(NOVOJOGO +" - Novo jogo dando um valor inicial\n"+ SAIR +" - Termina a execucao do programa\n"+ AJUDA +" - Mostra os comandos existentes");
    }
    public static void mainLeave(Scanner scan, FCTMil milObj){
        milObj.inMenu = false;
    }
    public static void gameEnd(Scanner scan, FCTMil milObj){
        milObj.inGame = false;
        System.out.print(milObj.exit());
    }
    public static void gameStart(Scanner scan, FCTMil milObj){
        double prizePool = scan.nextDouble();
        milObj.newGame(prizePool);
        System.out.print("Jogo iniciado. Valor do premio: ");
        System.out.printf("%.2f",prizePool);
        System.out.println(" Euros.");
        gameCommands(scan,milObj);
        
    }
    public static void gamePlay(Scanner scan, FCTMil milObj){
        int[] bets = new int[7];
        for(int i = 0;i<7;i++){
            bets[i] = scan.nextInt();
        }
        int prizeLevel = milObj.bet(bets);
        if (prizeLevel != 0){
            System.out.println("Obrigado pela aposta.  Premio de nivel: "+prizeLevel);
        }else {
            System.out.println("Chave incorrecta.");
        }
    }
    public static void gameHelp(Scanner scan, FCTMil milObj){
        System.out.println(JOGAR + " - Simula uma aposta, dando uma chave\n" + FIM + " - Termina o jogo em curso\n" + AJUDA + " - Mostra os comandos existentes");
    }
}