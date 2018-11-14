//import java.util.Arrays;
class FCTMil{
    private int[] winnerArray;
    private Double prizePool;
    private boolean inGame;
    private boolean inMenu;
    private IteratorInt starIte;
    private IteratorInt numIte;
    private final int STARS;
    private final int NUMBERS;
    Key keyC;
    public FCTMil(){
    winnerArray = new int[13];
    inGame = false;
    inMenu = true;
    STARS = 2;
    NUMBERS = 5;
    prizePool = 0d;
    }
    /**
     * @param prizePool game prize pool
     */
    public void newGame(Double prizePool){
        this.prizePool += prizePool;
        for(int i=0; i<winnerArray.length; i++){
            winnerArray[i] = 0;
        }
        keyC = new Key();
        starIte = keyC.criaIteratorStars();
        numIte = keyC.criaIteratorNumbers();
        inGame = true;
    }
    // Betting is directed here
    public int bet(int[] bets){
        boolean invalid = isValid(bets);
        int[] currentKey = getKey();
        //System.out.println(Arrays.toString(bets));
        int prizeNum = -1;
        if (!invalid)
        prizeNum = prize(currentKey,bets);
        if (prizeNum>0){
            winnerArray[prizeNum-1]++;
        }
        //System.out.println(Arrays.toString(winnerArray));
        return prizeNum;
    }
    private boolean isValid(int[] key){
        boolean invalid = false;
        for(int i = 0; i<NUMBERS;i++){
            for(int a = 0; a<NUMBERS;a++){
                if (i!=a)
                if(key[i]==key[a]){
                    a = NUMBERS;
                    i = NUMBERS;
                    invalid = true;
                }
            }
        }
        for(int i = 0; i<NUMBERS;i++){
            if (key[i]>50 ||key[i]<1)
            invalid = true;
        }
        for(int i = NUMBERS; i<NUMBERS+STARS;i++){
            if (key[i]>12 ||key[i]<1)
            invalid = true;
        }
        if(key[key.length-2]==key[key.length-1])
        invalid = true;
        return invalid;
    }
    // Return current key array
    private int[] getKey(){
        int [] key = new int[7];
        for(int i = 0;numIte.hasNextInt();i++){
            key[i]=numIte.nextInt();
        }
        for(int i = 0;starIte.hasNextInt();i++){
            key[i+NUMBERS]=starIte.nextInt();
        }
        numIte.reinitialize();
        starIte.reinitialize();
        return key;
    }
    // Takes in an int[] with the correct key and another one with the placed bet and outputsan int with the prize level, or 0 if theres is no prize
    private int prize(int[] key,int[] bet){
        int hitNums = 0;
        int hitStars = 0;
        for(int i = 0;i<NUMBERS;i++){
            for(int a = 0;a<NUMBERS;a++){
                if(key[a] == bet[i]){
                    hitNums++;
                }
            }
        }
        for(int i = NUMBERS;i<NUMBERS + STARS;i++){
            for(int a = NUMBERS;a<NUMBERS + STARS;a++){
                if(key[a] == bet[i]){
                    hitStars++;
                }
            }
        }
        // Since hitNums and hitStars are both lower than 10 I can use them as a sort of index for the switch statement
        int prizeSelector = 10*hitNums + hitStars;
        switch(prizeSelector){
            case 52:
                prizeSelector = 1;
                break;
            case 51:
                prizeSelector = 2;
                break;
            case 50:
                prizeSelector = 3;
                break;
            case 42:
                prizeSelector = 4;
                break;
            case 41:
                prizeSelector = 5;
                break;
            case 32:
                prizeSelector = 6;
                break;
            case 40:
                prizeSelector = 7;
                break;
            case 22:
                prizeSelector = 8;
                break;
            case 31:
                prizeSelector = 9;
                break;
            case 30:
                prizeSelector = 10;
                break;
            case 12:
                prizeSelector = 11;
                break;
            case 21:
                prizeSelector = 12;
                break;
            case 20:
                prizeSelector = 13;
                break;
            default:
                prizeSelector = 0;
                break;
        }
        return prizeSelector;
    }
    public String exit(){
        String exitString = "";
        String formattedNumber = "";
        Double[] percent = {43.2,4.15,1.92,1.45,1.48,1.67,1.38,1.75,2.85,4.1,4.95,13.85,17.25};
        // Replace percent array with each prizes value
        for (int i = 0;percent.length>i;i++){
            percent[i]=prizePool*percent[i]*0.01/winnerArray[i];
        }
        //System.out.println(Arrays.toString(percent));

        for (int i = 0;winnerArray.length>i;i++){
            if(percent[i]!=1/0f){
                formattedNumber = String.format("%.2f",percent[i]).replaceAll(",",".");
                // Nivel: 1 Jogadores: 999 Valor premio: 999999,99 Euros
                exitString += "Nivel: " + (i+1) + " Jogadores: " + winnerArray[i] + " Valor premio: " + formattedNumber+ " Euros\n";
            }else{
                exitString += "Nivel: " + (i+1) + " Jogadores: " + winnerArray[i] + "\n";
            }
            
        }
        // Change remaining cash money
        for (int i=0; i<percent.length;i++){
            if (percent[i]!=1/0f)
            prizePool -= percent[i]*winnerArray[i];
        }
        if (prizePool<0){
            prizePool = 0d;
        }

        formattedNumber = String.format("%.2f",prizePool).replaceAll(",",".");
        exitString += "Valor acumulado: " + formattedNumber+" Euros\n";
        //System.out.println("exitString: " + exitString);
        return exitString;
    }
    public String bye(){
        // Valor acumulado: 0.00 Euros. Ate a proxima.
        
        return "Valor acumulado: "+String.format("%.2f",prizePool).replaceAll(",",".")+" Euros. Ate a proxima.";
    }
    // Game state operators
    public boolean getGameState(){
        return inGame;
    }
    public void falseGame(){
        inGame = false;
    }
    public boolean getMenuState(){
        return inMenu;
    }
    public void falseMenu(){
        inMenu = false;
    }
    public Double getprizepool(){
        return prizePool;
    }
}