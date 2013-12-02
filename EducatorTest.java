import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LessonUploadTest {

/**
 * @throws Exception
 */
	String fileLocation1 = "Files/"; //the file unitTest.txt must be located in the Files directory for this test to pass
	String fileLocation2 = "src/Subjects/Math/Algebra/Lesson1/";
	boolean result = false;
	String subject = "Math";
	String category = "Algebra";
	String lesson = "Lesson1";
	String title = "Lesson1: Test";
	String fileName = "unitTest.txt";
	
	@Before
	public void setUp() throws Exception {
		// Create directories to store files and create an index file
		Educator.makeLesson(subject, category, lesson);
		//add file from fileLocation1 to fileLocation2 to lesson
		//result = Educator.copyFile(fileLocation1, fileLocation2);
		result = Educator.addFileToLesson(fileLocation1, fileLocation2, title, fileName);
	}

	@Test
	public void test() {
		assertTrue(result); // file was successfully added to lesson
		// verify that files unitTest.txt and index.txt are located in the destination folder.
		// verify that index.txt contains the file information
	}

}
