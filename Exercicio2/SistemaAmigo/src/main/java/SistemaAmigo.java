import Exceptions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo() {
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoExistenteException {
        Amigo amigoComparacao = new Amigo(nomeAmigo,emailAmigo,"");
        for(Amigo amigoCadastra: this.amigos) {
            if(amigoCadastra.getNome().equals(amigoComparacao.getNome()) &&
                    amigoCadastra.getEmail().equals(amigoComparacao.getEmail())) {
                throw new AmigoExistenteException("Esse amigo já existe no Sistema");
            }

        }
        Amigo novoAmigo = new Amigo(nomeAmigo,emailAmigo,"");
        this.amigos.add(novoAmigo);
    }

    public Amigo pesquisaAmigo(String amigo) throws ListaVaziaException,AmigoInexistenteException {
        if (amigos.isEmpty()) {
            throw new ListaVaziaException("A lista de amigos está vazia!");
        }
        for (Amigo amigoPesquisado: amigos) {
            if (amigoPesquisado.getNome().equals(amigo)) {
                return amigoPesquisado;
            }
        }
        throw new AmigoInexistenteException("Amigo Inexistente");
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        mensagens.add(new MensagemParaTodos(texto,emailRemetente,ehAnonima));
    }

    public void enviarMensagemParaAlguem(String texto,String emailRemetente,
                                         String emailDestinatario, boolean ehAnonima) {
        mensagens.add(new MensagemParaAlguem(texto,emailRemetente,emailDestinatario,ehAnonima));
    }

    public List<Mensagem> pesquisaMensagensAnonimas() throws ListaVaziaException {
        if(mensagens.isEmpty()){
            throw new ListaVaziaException("A lista de mensagens está vazia!");
        }
        List<Mensagem> listMensagensAnonimas = new LinkedList<>();
        for(Mensagem mensagemAnonima: mensagens) {
            if(mensagemAnonima.ehAnonima()) {
                listMensagensAnonimas.add(mensagemAnonima);
            }
        }
        if (listMensagensAnonimas.isEmpty()) {
            throw new ListaVaziaException("Não existe mensagens anônimas");
        }
        return listMensagensAnonimas;
        }

    public List<Mensagem> pesquisaTodasAsMensagens() throws ListaVaziaException{
    if(mensagens.isEmpty()) {
        throw new ListaVaziaException("Não Existem mensagens!");
    }
    return mensagens;
}

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws ListaVaziaException,
                                                                                                AmigoInexistenteException{
        if(this.amigos.isEmpty()) {
            throw new ListaVaziaException("A lista de amigos está vazia!");
        }
        for (Amigo amigoPesquisado: amigos) {
             if (amigoPesquisado.getEmail().equals(emailDaPessoa)) {
                amigoPesquisado.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }
        }
        throw new AmigoInexistenteException("Esse amigo não existe!");
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, ListaVaziaException,
                                                                        AmigoSorteadoNaoExisteException{
        if(amigos.isEmpty()) {
        throw new ListaVaziaException("A lista de amigos está vazia!");
    }
        for(Amigo amigoSecretoDe: amigos) {
            if (amigoSecretoDe.getEmail().equals(emailDaPessoa)){
                if(amigoSecretoDe.getEmailAmigoSorteado().equals("")) {
                    throw new AmigoSorteadoNaoExisteException("Não houve um sorteio para esse amigo!");
                } else {
                    return amigoSecretoDe.getEmailAmigoSorteado();
                }
            }
        }
        throw new AmigoInexistenteException("Esse email não consta para nenhum amigo!");
    }

    public void sortear() throws ListaVaziaException, AmigoSorteadoJaExisteException {
        if(this.amigos.isEmpty()) {
            throw new ListaVaziaException("A lista está vazia");
        }
        for(Amigo verificaSeJaHouveSorteio: amigos){
            if(!(verificaSeJaHouveSorteio.getEmailAmigoSorteado().equals(""))){
                throw new AmigoSorteadoJaExisteException("Já houve um sorteio!");
            }
        }
        List<Amigo> amigosNaoSorteados = this.amigos;
        while (!(amigosNaoSorteados.size() == 0)) {
            int posicaoDaListaSorteada = (int) Math.random()*amigosNaoSorteados.size();
            int posicaoDaListaSorteada2 = (int) Math.random()*amigosNaoSorteados.size();

            if(     (!amigosNaoSorteados.get(posicaoDaListaSorteada).getNome().equals(amigosNaoSorteados.get(posicaoDaListaSorteada2).getNome())) &&
                    (amigosNaoSorteados.get(posicaoDaListaSorteada).equals(amigosNaoSorteados.get(posicaoDaListaSorteada2)))) {
                            amigosNaoSorteados.get(posicaoDaListaSorteada).setEmailAmigoSorteado(amigosNaoSorteados.
                            get(posicaoDaListaSorteada2).getEmailAmigoSorteado());
            }
        }
    }

    public int tamanho() {
        return this.amigos.size();
    }
}
