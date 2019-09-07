package bd.edu.seu.grademanagementserver.exception;

public class ResourceDoesNotExistException extends  Exception {
    public ResourceDoesNotExistException(String resource) {
        super(resource + " does not exist");
    }
}
