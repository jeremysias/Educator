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
	
	
	/*
	 * This program performs functions necessary for creating a file system
	 * The heierarchy is as follows: Subject -> Category -> Lesson
	 * Each lesson folder will have an index file which keeps track of files within the lesson
	 * 
	 * commands available:
	 * 	makeSubject
	 * 	makeCategory
	 *  makeLesson
	 *  write -- writes to index file
	 *  addItem -- will take a file from the "Files" folder and put it into the lesson folder
	 *  	It also adds the item to the index file
	 *  
	 */
	
	  //makes a directory -- this should not be called directly
	  public static boolean _makeFolder(String folderName){
	    File file = new File(folderName);
	    return file.mkdirs();
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
	    String folder = "src/Subjects/" + subjectName;
	    return _makeFolder(folder);
	  }
	  
	  public static boolean makeLessonCategory(String subjectName, String CategoryName){
	    String folder = "src/Subjects/" + subjectName + "/" + CategoryName;
	    return _makeFolder(folder);
	  }
	  
	  //creates a lesson
	  public static boolean makeLesson(String subjectName, String CategoryName, String lessonName){
	    boolean result = false;
		String folder = "src/Subjects/" + subjectName + "/" + CategoryName + "/" + lessonName;
	    result =  _makeFolder(folder);
	  //  result = copyFile("Files/index.html", folder + "/");
	    result = createIndexFile(folder);
	    
	    return result;
	  }
	  
	  //copies a file
	  //parameters: in - path and name of file to be copied
	  //			out - path and name of destination file
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
	  
/*		I may need this incomplete function later
 *   public static String getFileName(String path){
		  
		  char[] pathCharArray = path.toCharArray();
		  
		  
		  String fileName;
		  return fileName;
	  }
	  
*/  
	  
	  /* addFileToLesson() Takes an item from a folder and copies it into the lesson folder
	  	Also adds a title and fileName to the index using writeToIndex()
	  		parameters:
	  			pathIn - location of file (but not the file)
	    		pathOut - location of destination(but not the file)
	    		title - Title of the lesson Item
	    		fileName - name of file to be uploaded
	   */
	  public static boolean addFileToLesson(String pathIn, String lessonPath, String title, String fileName){
		  pathIn = pathIn + fileName;
		  String outPath = lessonPath + fileName;
		  boolean results = copyFile(pathIn, outPath);
		  results = writeToIndex(lessonPath, title, fileName);
		  return results;
	  }
	  
	  
	  //This will be used upon creation of a lesson Folder
	  //The result will be a blank text file name "index.txt"
	  public static boolean createIndexFile(String lessonFolder){
		  try{
			  File file = new File(lessonFolder + "/index.txt");
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
			  File file = new File(lessonPath + "/index.txt");
			  
			  if(!file.exists()){
				  System.out.println("File does not exist");
				  return false;
				//  file.createNewFile();
			  }
			  
			  FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			  BufferedWriter bw = new BufferedWriter(fw);
		//	  bw.append(content);
			  bw.write(content);
			  bw.close();
			  
			  System.out.println("Done writing to file");
			  
			  
		  }catch(IOException e){
			  e.printStackTrace();
			  return false;
		  }
		  
		  return true;
	  }
	  
	  
	  //This may not end up being used 
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
	  //This function may or may not be used
	  public static boolean uploadItem(String item){
		  
		  return true;
	  }
	public static void main(String[] args) {
		String [] results = new String[1];
		results[0] = "Command not registered";
		int argLength = args.length;
		String cmd = args[0];
		if(cmd.equals("getSubjects")){
			String[] altResults = getFolderNames("src/Subjects/");
			int count = altResults.length;
			for(int i = 0; i< count; i++){
				
				System.out.println(altResults[i]);
				
			}
			return;
		}
		if(cmd.equals("makeSubject")){
			if(argLength > 1){
				if(makeSubject(args[1]) == true){
					System.out.println(args[1] + " created");
				}
				else{
					System.out.println(args[1] + "could not be created or already exists");
				}
			}
			else{
				System.out.println("parameter: <Subject name>");
			}
			return;
		}
		if(cmd.equals("makeCategory")){
			if(argLength > 2){
				if(makeLessonCategory(args[1], args[2]) == true){
					System.out.println(args[2] + " created");
				}
				else{
					System.out.println(args[2] + "could not be created or already exists");
				}
			}
			else{
				System.out.println("parameters: <Subject name> <Category name>");
			}
			return;
		}
		if(cmd.equals("makeLesson")){
			if(argLength > 3){
				if(makeLesson(args[1], args[2], args[3]) == true){
					System.out.println(args[3] + " created");
				}
				else{
					System.out.println(args[3] + "could not be created or already exists");
				}
			}
			else{
				System.out.println("parameters: <Subject name> <Category name> <Lesson name>");
			}
		}
		if(cmd.equals("write")){
			if(argLength > 5){
				String path = "src/Subjects/" + args[1] + "/" + args[2] + "/" + args[3];
				if(writeToIndex(path, args[4], args[5]) == true){
					System.out.println("Index written to successfully");
				}
				else{
					System.out.println("write failed");
				}
			}
			else
				System.out.println("parameters: <Subject> <Category> <Lesson> <title> <filename>");
		}
	
		if(cmd.equals("addItem")){
			if(args.length > 5){
				String subject = args[1];
				String category = args[2];
				String lesson = args[3];
				String path1 = "Files/";
				String path2 = "src/Subjects/" + subject + "/" + category + "/" + lesson +"/";
				String title = args[4];
				String fileName = args[5];
				addFileToLesson(path1, path2, title, fileName);
			}
			else{
				System.out.println("parameters: <Subject> <Category> <Lesson> <Title> <file name>");
				return;
			}
		}
		
		// TODO Auto-generated method stub
		
		return;
	}

}
