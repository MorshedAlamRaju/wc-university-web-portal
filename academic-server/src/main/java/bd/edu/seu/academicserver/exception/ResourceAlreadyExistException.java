package bd.edu.seu.academicserver.exception;

public class ResourceAlreadyExistException extends  Exception {
    public ResourceAlreadyExistException(String resource) {
        super(resource + " already exist!!");
    }
}
