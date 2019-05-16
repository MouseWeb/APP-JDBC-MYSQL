package RuntimeException;

// Class informa a mensagem de exceção da conexão .
public class DbException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DbException(String msg) {
        super(msg);
    }

}
