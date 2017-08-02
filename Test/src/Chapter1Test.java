import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Chapter1Test {

	private static final String usage = "2:\n\t<File path>\n"
			+ "3:\n:\t<File path> <extension>"
			+ "4:\n:\t<File path> "
			+ "5:\n";
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println(usage);
		}
		switch(args[0]){
		case "2":
			if(args.length!=2){
				System.out.println(usage);
				return;
			}
			new Chapter1Test().exercise2(args[1]);
			break;
		case "3":
			if(args.length!=3){
				System.out.println(usage);
				return;
			}
			new Chapter1Test().exercise3(args[1], args[2]);
			break;
		case "4":
			if(args.length!=2){
				System.out.println(usage);
				return;
			}
			new Chapter1Test().exercise4(args[1]);
			break;
		case "7":
			new Chapter1Test().exercise7();
			break;
		}
		

	}
	
	/**
	 * Using the <code>listFiles(Filter)</code> and <code>isDirectory</code> methods of the <code>java.io.File</code> class, 
	 * write a method that returns all subdirectories of a given directory. 
	 * Use a lambda expresion instead of a FileFilter object and repeat with a method reference.
	 * @param path
	 */
	public void exercise2(String path){
		File file = new File(path);
		System.out.println("Listing with lambda expression:");
		File[] directories = file.listFiles((File f)->f.isDirectory());
		
		for(File f:directories){
			System.out.println(f);
		}
		
		System.out.println("Listing with method reference:");
		directories = file.listFiles(File::isDirectory);
		for(File f:directories){
			System.out.println(f);
		}
		
		
	}
	
	/**
	 * Using the list(<code>FilenameFilter</code> method of the <code>java.io.File</code> class,
	 * write a method that returns all files in a given directory with a given extension.
	 * Use a lambda expression, not a FilnameFilter.
	 * @param path
	 * @param ext
	 */
	public void exercise3(String path, String ext){
		File file = new File(path);
		System.out.println("Listing file with extension ."+ext+":");
		File[] files = file.listFiles((File f)->{
			String fileName = f.getName();
			int i = fileName.lastIndexOf(".");
			if(fileName.substring(i+1).equals(ext)){
				return true;
			}
			return false;
		});
		
		for(File f:files){
			System.out.println(f);
		}
		
	}
	
	/**
	 * Given an array of <code>File</code> objects, sort it so that the directories come before the files,
	 * and within each group, elements are sortet by path name. Use a lamba expresion not <code>Comparator</code>.
	 * @param path
	 */
	public void exercise4(String path){
		File[] files = new File(path).listFiles();
		
		Arrays.sort(files, (a, b)->{
			if(a.isDirectory()){
				if(!b.isDirectory()){
					return -1;
				}
			}
			else if(b.isDirectory()){
				return 1;
			}
			
			return a.getPath().compareTo(b.getPath());
		});	
		
		for(File f:files){
			System.out.println(f);
		}
		
	}
	
	
	/**
	 * Write a static method <code>andThen</code> that takes as parameters
	 * two <code>Runable</code> instances and return a <code>Runable</code> 
	 * that runs the firs, then the second. In the <code>main<code> method, 
	 * pass two into a call to<code>andThen</code>, and run the returned instance.
	 */
	public void exercise7(){
		Runnable r1 = ()->{	System.out.print("Hello World ");};
		Runnable r2 = ()->{ System.out.println("today is "+(new Date()));};
		
		Runnable finalRun = Chapter1Test.andThen(r1, r2);
		finalRun.run();
		
		
	}
	
	public static Runnable andThen(Runnable r1, Runnable r2){
		return ()->{
			r1.run();
			r2.run();
		};
	}

}
