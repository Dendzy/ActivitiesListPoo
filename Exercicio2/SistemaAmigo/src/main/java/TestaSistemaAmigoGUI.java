
import Exceptions.AmigoExistenteException;
import Exceptions.AmigoInexistenteException;
import Exceptions.AmigoSorteadoJaExisteException;
import Exceptions.ListaVaziaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestaSistemaAmigoGUI {
    public static void main(String [] args) {

        SistemaAmigo control = new SistemaAmigo();
        Scanner input = new Scanner(System.in);

        System.out.println("Digite a quantidade total de amigos: ");
        int quantTotalDeAmigos = Integer.parseInt(input.nextLine());
        int i = 0;
        while(quantTotalDeAmigos > i) {
            System.out.println("Digite o nome do amigo " + (i + 1));
            String nome = input.nextLine();
            System.out.println("Agora digite o email dele: ");
            String email = input.nextLine();
            try {
                control.cadastraAmigo(nome, email);
            } catch (AmigoExistenteException e) {
                e.printStackTrace();
            }
            i++;
        }
            System.out.println("Envie uma mensagem para todos: ");
            System.out.println("Digite seu email: ");
            String emailParaEnviar = input.nextLine();
            System.out.println("Digite o texto: ");
            String textoParaEnviar = input.nextLine();
            System.out.println("A mensagem é anônima?");
            String ehAnonimaParaEnviarString = input.nextLine();
            boolean ehAnonimaParaEnviar = false;
            if (ehAnonimaParaEnviarString.equalsIgnoreCase("1")) {
                ehAnonimaParaEnviar = true;
            }
            control.enviarMensagemParaTodos(textoParaEnviar,emailParaEnviar,ehAnonimaParaEnviar);


        List<Mensagem> mensagensTotais = new ArrayList<>();

        try {
            mensagensTotais = control.pesquisaTodasAsMensagens();
        } catch (ListaVaziaException e) {
            e.getMessage();
        }

        for(int j = 0; mensagensTotais.size() > j; j++) {
            System.out.println(mensagensTotais.get(j).getTextoCompletoAExibir());
        }

    }
}
