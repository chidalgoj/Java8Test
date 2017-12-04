import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Chapter2Test {

	private static final String usage = "3:\n\t<File path>\n"
			+ "5:\n\ta c m seed limit";
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println(usage);
		}
		switch(args[0]){
		case "3":
			if(args.length!=2){
				System.out.println(usage);
				return;
			}
			new Chapter2Test().exercise3(args[1]);
			break;
		case "5":
			if(args.length!=6){
				System.out.println(usage);
				return;
			}
			new Chapter2Test().exercise5(Long.parseLong(args[1]), Long.parseLong(args[2]), Long.parseLong(args[3]), Long.parseLong(args[4]), Integer.parseInt(args[5]));
			break;
		
		}
	}
	
	/**
	 * Measure the difference when counting long words with a <code>parallelStream</code> instead of a <code>stream</code>.
	 * Call <code>System.nanoTime</code> before and after the call, and print the difference.
	 * 
	 * @param txt file
	 * @throws IOException 
	 */
	private void exercise3(String file) {
		try{
			String contents = new String(Files.readAllBytes(Paths.get(file)));
			List<String> words = Arrays.asList(contents.split("[\\P{L}+]"));
			long time, count;
			System.out.println("Counting with Stream");
			time = System.nanoTime();
			count = words.stream().filter(w -> w.length() > 10).count();
			System.out.println(count + " words with Stream in " + (System.nanoTime()-time) + " ms");
			
			System.out.println("Counting with ParallelStream");
			time = System.nanoTime();
			count = words.parallelStream().filter(w -> w.length() > 10).count();
			System.out.println(count + " words with ParallelStream in " + (System.nanoTime()-time) + " ms");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Using <code>Stream.iterate</code>, make an infinite stream of random numbers, not by calling 
	 * <code>Math.random</code> but by directly implementing a <i>linear congruential generator</i>.
	 * In such a generator, you start with <i>x(0) = seed</i> and then produce <i>x(n+1) = (ax(n)+c)%m</i>
	 * for appropriate values <i>a, c</i> and <i>m</i>. You should implement a method with pameters 
	 * <i>a, c, m</i> and <i>seed</i> that yields as <code>Stream&ltLong&gt</code>
	 * @param string
	 * @param string2
	 */
	private void exercise5(long a, long c, long m, long seed, int limit) {  
		
		Stream.iterate(seed, (Long l)->(a*l+c)%m).limit(limit).forEach(System.out::println);;
		
	}

	

}
