package hu.dbx.gwt.test.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1440697994706069836L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    						throws ServletException, IOException {

		response.setContentType("text/plain");

		List<FileItem> uploadItems = getFileItems(request);
		if (uploadItems == null) {
			response.getWriter().write("NO-SCRIPT-DATA");
			return;
		}
		
		byte[] nameContents = uploadItems.get(0).get();
		byte[] descContents = uploadItems.get(1).get();
		byte[] fileContents = uploadItems.get(2).get();
		//TODO: add code to process file contents here. We will just print
		System.out.println("name: " + new String(nameContents));
		System.out.println("description: " + new String(descContents));
		System.out.println("file tartalom: " + new String(fileContents));
		
		//response.getWriter().write(new String(fileContents));
		response.getWriter().write(new String("Sikeres feltöltés!"));
	}

	@SuppressWarnings("unchecked")
	private List<FileItem> getFileItems(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
			List<FileItem> items;
			try {
				items = (List<FileItem>) upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return items;
			
	}
}
