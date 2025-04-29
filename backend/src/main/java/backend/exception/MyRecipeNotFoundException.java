package backend.exception;

public class MyRecipeNotFoundException  extends  RuntimeException{
    public MyRecipeNotFoundException(Long id){
        super("could not find id" + id);
    }
    public MyRecipeNotFoundException(String message){
        super(message);
    }
}
