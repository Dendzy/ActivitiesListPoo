public class MensagemParaAlguem extends Mensagem {
    private String emailDestinatario;
    public MensagemParaAlguem(String texto,String emailRemetente,String emailDestinatario,
                                boolean anonima) {
        super(texto,emailRemetente,anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getTextoCompletoAExibir() {
        if(ehAnonima()) {
            String emailDestinatarioString = this.emailDestinatario;
            String texto = getTexto();
           return  "Mensagem para: \""  + emailDestinatarioString +
                   "\" enviado de forma anônima!" +
                    "\nTexto: " + texto;
        }
        else {
            String emailDestinatatioString = this.emailDestinatario;
            String texto = getTexto();
            String emailRemetente = getEmailRemetente();
            return "De: " + emailRemetente +
                    "\nPara: \"" + emailDestinatatioString + "\"" +
                    "\nTexto: " + texto;
        }
    }
}
