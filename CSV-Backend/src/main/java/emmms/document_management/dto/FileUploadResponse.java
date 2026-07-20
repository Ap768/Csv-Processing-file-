	package emmms.document_management.dto;
	
	public class FileUploadResponse {
	
	    private String fileName;
	    private String dateTime;
	    private String status;
	
	    public FileUploadResponse(String fileName, String dateTime, String status) {
	        this.fileName = fileName;
	        this.dateTime = dateTime;
	        this.status = status;
	    }
	
	    public FileUploadResponse(String fileName, String dateTime) {
	        this.fileName = fileName;
	        this.dateTime = dateTime;
	        this.status = "Uploaded";
	    }
	
	    public String getFileName() {
	        return fileName;
	    }
	
	    public String getDateTime() {
	        return dateTime;
	    }
	
	    public String getStatus() {
	        return status;
	    }
	}