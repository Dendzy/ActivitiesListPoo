import Exceptions.*;

import java.util.*;

public class SistemaAmigoMap {
    private List<Mensagem> mensagens;
    private Map<String, Amigo> amigos;

    public SistemaAmigoMap() {
        this.mensagens = new ArrayList<>();
        this.amigos = new HashMap<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoExistenteException {
        Amigo amigoComparacao = new Amigo(nomeAmigo,emailAmigo,"");
        for(Amigo amigoCadastra: this.amigos.values()) {
            if(amigoCadastra.getNome().equals(amigoComparacao.getNome()) &&
                    amigoCadastra.getEmail().equals(amigoComparacao.getEmail())) {
                throw new AmigoExistenteException("Esse amigo já existe no Sistema");
            }

        }
        Amigo novoAmigo = new Amigo(nomeAmigo,emailAmigo,"");
        this.amigos.put(novoAmigo.getEmail(),novoAmigo);
    }

    public Amigo pesquisaAmigo(String amigo) throws AmigoInexistenteException {
        if (amigos.isEmpty()) {
            throw new AmigoInexistenteException("A lista de amigos está vazia!");
        }
        for (Amigo amigoPesquisado: amigos.values()) {
            if (amigoPesquisado.getEmail().equals(amigo)) {
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
        List<Mensagem> listMensagensAnonimas = new LinkedList<>();
        for(Mensagem mensagemAnonima: mensagens) {
            if(mensagemAnonima.ehAnonima()) {
                listMensagensAnonimas.add(mensagemAnonima);
            }
        }
        return listMensagensAnonimas;
        }

    public List<Mensagem> pesquisaTodasAsMensagens() throws ListaVaziaException{
    return mensagens;
}

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws ListaVaziaException,
                                                                                                AmigoInexistenteException{
        if(this.amigos.isEmpty()) {
            throw new ListaVaziaException("A lista de amigos está vazia!");
        }
        for (Amigo amigoPesquisado: amigos.values()) {
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
        for(Amigo amigoSecretoDe: amigos.values()) {
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
}
