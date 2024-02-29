package br.com.dandy;

public class ClienteNaoExisteException extends Exception {
    public ClienteNaoExisteException(String msg) {
        super(msg);
    }
}
