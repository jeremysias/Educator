import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Educator {
	  //makes a directory -- this should not be called directly
	  public static boolean _makeFolder(String folderName){
	    File file = new File(folderName);
	    return file.mkdir();
	  }
	  
	  //can be used to delete a folder
	  //we will want to be careful about how we implement this on the main server
	  //if a user decides to delete something on the main server, it should first save it
	  //to an archive folder before deleting it to prevent unfortunate mishaps
	  public static boolean remove(String fileName){
	    File file = new File(fileName);
	    return file.delete();
	  }
	  
	  public static boolean makeSubject(String subjectName){
	    String folder = "Subject/" + subjectName;
	    return _makeFolder(folder);
	  }
	  
	  public static boolean makeLessonCategory(String subjectName, String CategoryName){
	    String folder = "Subject/" + subjectName + "/" + CategoryName;
	    return _makeFolder(folder);
	  }
	  
	  public static boolean makeLesson(String subjectName, String CategoryName, String lessonName){
	    boolean result = false;
		String folder = "Subject/" + subjectName + "/" + CategoryName + "/" + lessonName;
	    result =  _makeFolder(folder);
	    result = copyFile("Files/index.html", folder + "/");
	    
	    return result;
	  }
	  
	  public static boolean copyFile(String in, String out){
	    	InputStream inStream = null;
	    	OutputStream outStream = null;
		  
	    	try{
	 
	    	   File afile =new File(in);
	    	   File bfile = new File(out);
	    	   
	    	   inStream = new FileInputStream(afile);
	    	   outStream = new FileOutputStream(bfile);
	    	   
	    	   byte[] buffer = new byte[1024];
	    	   
	    	   int length;
	    	   
	    	   while((length = inStream.read(buffer)) >0){
	    		   outStream.write(buffer, 0, length);
	    	   }
	    	   
	    	   inStream.close();
	    	   outStream.close();
	    	   
	    	   System.out.println("File copied successfully");
	    	   return true;
	    	}catch(IOException e){
	    		e.printStackTrace();
	    		return false;
	    	}
	   
	  }
	  
	  
	  //This will be used upon creation of a lesson Folder
	  //The result will be a blank text file name "index.txt"
	  public static boolean createIndexFile(String lessonFolder){
		  try{
			  File file = new File(lessonFolder + "index.txt");
			  if(file.createNewFile()){
				  System.out.println("File created successfully");
				  return true;
			  }
			  else{
				  System.out.println("File exists already");
				  return false;
			  }
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  return false;
	  }
	  
	  public static boolean writeToIndex(String lessonPath, String title, String fileName){
		  try{
			  String content = title + ";" + fileName + "|";
			  File file = new File(lessonPath + "index.txt");
			  
			  if(!file.exists()){
				  file.createNewFile();
			  }
			  
			  FileWriter fw = new FileWriter(file.getAbsoluteFile());
			  BufferedWriter bw = new BufferedWriter(fw);
			  bw.write(content);
			  bw.close();
			  
			  System.out.println("Done writing to file");
			  
			  
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  
		  return false;
	  }
	  
	  public static void displayFolderNames(String path){
	    File file = new File(path);
	    File []files = file.listFiles();
	    for (File f : files){
	      if (f.isDirectory()){
	        String dirName = f.getName();
	        System.out.println(dirName);
	        System.out.println(" ");
	      }
	    }
	  }
	  public static String[] getFolderNames(String path){
		  
		  File file = new File(path);
		  File[]files = file.listFiles();
		  int fileCount = 0;
		  for(File f : files){
			  if(f.isDirectory()){
				  fileCount ++;  
			  }
		  }
	//	  fileCount = fileCount -2;
		  String[] subjects = new String[fileCount];
		  int i = 0;
		  for(File f : files){
			  if(f.isDirectory()){
				  if(f.getName() != "." && f.getName()!=".."){
					  subjects[i] = f.getName();
					  i++;
				  }
			  }
		  }
		  return subjects;
	  }
	/**
	 * @param args
	 */
	  public static boolean uploadItem(String item){
		  
		  return true;
	  }
	public static void main(String[] args) {
		String [] results = new String[1];
		results[0] = "Command not registered";
		String cmd = args[0];
		if(cmd.equals("getSubjects")){
			String[] altResults = getFolderNames(".");
			int count = altResults.length;
			for(int i = 0; i< count; i++){
				
				System.out.println(altResults[i]);
				
			}
			return;
		}
		
		// TODO Auto-generated method stub
		
		return;
	}

}
