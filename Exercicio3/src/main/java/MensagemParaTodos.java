public class MensagemParaTodos extends Mensagem {
    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto,emailRemetente,false);
    }

    @Override
    public String getTextoCompletoAExibir() {
        if(ehAnonima()) {
            String texto = getTexto();
            return  "Esse email foi enviado para todos de forma anônima" +
                    "\nTexto: " + texto;
        }
        else {
            String texto = getTexto();
            String emailRemetente = getEmailRemetente();
            return "De: \"" + emailRemetente + "\"" +
                    "\nPara: Todos" +
                    "\nTexto: \n" + texto;
        }
    }


}
