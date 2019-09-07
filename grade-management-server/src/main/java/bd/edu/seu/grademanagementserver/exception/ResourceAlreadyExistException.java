package bd.edu.seu.grademanagementserver.exception;

public class ResourceAlreadyExistException extends  Exception {
    public ResourceAlreadyExistException(String resource) {
        super(resource + " already exist!!");
    }
}
