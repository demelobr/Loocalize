package exception;

public class UsuarioLogadoComSucessoException extends Exception{
    private String fxml;
    private String title;

    public UsuarioLogadoComSucessoException(String fxml, String title){
        super("Login feito com sucesso!");
        this.fxml = fxml;
        this.title = title;
    }

    public String getFxml(){
        return fxml;
    }

    public void setFxml(String fxml){
        this.fxml = fxml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
