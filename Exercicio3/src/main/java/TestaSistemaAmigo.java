import Exceptions.AmigoExistenteException;
import Exceptions.AmigoInexistenteException;
import Exceptions.AmigoSorteadoNaoExisteException;
import Exceptions.ListaVaziaException;

import java.util.LinkedList;
import java.util.List;

public class TestaSistemaAmigo {
    public static void main(String [] args) {
        SistemaAmigoMap sistema = new SistemaAmigoMap();
        List<Mensagem> mensagensAnonimas = new LinkedList<>();
        try {
            sistema.cadastraAmigo("Jose","jose@dcx.ufpb");
            sistema.cadastraAmigo("Maria","maria@dcx.ufpb");
        } catch (AmigoExistenteException e) {
            e.printStackTrace();
        }

        try {
            sistema.configuraAmigoSecretoDe("jose@dcx.ufpb","maria@dcx.ufpb");
        } catch (AmigoInexistenteException | ListaVaziaException e) {
            e.printStackTrace();
        }

        sistema.enviarMensagemParaAlguem("Olá José","maria@dcx.ufpb",
                            "jose@dcx.ufpb",true);
        sistema.enviarMensagemParaTodos("Olá todos","maria@dcx.ufpb", true);

        try {
            mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
        } catch (ListaVaziaException e) {
            throw new RuntimeException(e);
        }
        for(Mensagem mensagens: mensagensAnonimas) {
            System.out.println(mensagens.getTextoCompletoAExibir());
        }

        try {
            System.out.println(sistema.pesquisaAmigoSecretoDe("jose@dcx.ufpb"));
        } catch (ListaVaziaException | AmigoSorteadoNaoExisteException | AmigoInexistenteException e) {
            e.printStackTrace();
        }

    }
}
