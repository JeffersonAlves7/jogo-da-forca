import java.util.List;
import java.util.Scanner;

public class JogoDaForca {
    private final List<String> words = List.of("COMPUTADOR", "TELEFONE", "NOTEBOOK");
    private String wordSelected;
    private String lettersFounded;
    private int errors = 0;

    public void start(){
        wordSelected = selectWord();
        lettersFounded = "_".repeat(wordSelected.length());
        System.out.println("Bem-vindo ao jogo da forca, sua palavra é: " + lettersFounded);

        do {
            String letter = chooseWordOrLetter();
            verifyWordOrLetter(letter);
            drawGame();
            if(lettersFounded.equals(wordSelected)) break;
        } while(errors < 3);

        if(errors == 3)
            System.out.println("Você perdeu :( a palavra era \""+ wordSelected + "\"");
        else
            System.out.println("Você ganhou!");
     }

    private String selectWord(){
        int i = (int) (Math.random() * words.size());
        return words.get(i);
    }

    private String chooseWordOrLetter(){
        String userInput = "";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha uma letra ou palavra completa: ");
        do {
            String input = scanner.next().trim().toUpperCase();
            if(input.length() < 1) {
                System.out.print("Escolha uma letra ou palavra completa válida: ");
                continue;
            }
            userInput = input;
        } while(userInput.length() < 1);

        return userInput;
    }

    private void verifyWordOrLetter(String wordOrLetter){
        if(wordOrLetter.length() > 1 && wordOrLetter.equals(wordSelected)){
            lettersFounded = wordOrLetter;
            return;
        }

        int correct = 0;
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < wordSelected.length(); i++){
            if(!(wordSelected.charAt(i) == wordOrLetter.charAt(0))){
                newString.append(lettersFounded.charAt(i));
            }else{
                correct++;
                newString.append(wordSelected.charAt(i));
            }
        }

        if(correct == 0){
            errors++;
        }else{
            lettersFounded = newString.toString();
        }
    }

    private void drawGame(){
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" ---------");
        System.out.println(" |       |");
        System.out.println(" |      " + (errors > 0 ? "😿": ""));
        System.out.println(" |      " + (errors > 1 ? "💪": ""));
        System.out.println(" |      " + (errors > 2 ? "🦿": ""));
        System.out.println(" |");
        System.out.println("_|_");

        System.out.println(lettersFounded);
    }
}
