import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vorlesung.TextFileFormatException;
import vorlesung.Vorlesungsverzeichnis;
import vorlesung.Vorlesung;

public class App {
    String filename = "db_junit.txt";
    PrintWriter pw;
    
    Class<Vorlesung> c = Vorlesung.class; // Klasse Vorlesung vorhanden?
    
	public void setUp() throws IOException
	{
        pw = new PrintWriter(filename);
    }

	public void Titles() throws IOException, TextFileFormatException
	{
	    List<String> al = new ArrayList<String>();
	    
		al.add("Elektrodynamik");
		al.add("Quantenmechanik");
		al.add("Quantenphysik");
        al.add("Relativitaetstheorie");
		al.add("Theoretische Physik");
		al.add("Thermodynamik");

		pw.print(
        		"A1:Relativitaetstheorie:Einstein:15\n" +
        		"B2:Quantenmechanik:Heisenberg:17\n" +
        		"C2:Quantenphysik:Planck:5\n" +
        		"T4:Thermodynamik:Kelvin:78\n" +
        		"C2:Theoretische Physik:Kelvin:54\n" +
        		"B2:Thermodynamik:Planck:44\n" +
        		"T4:Quantenphysik:Planck:45\n" +
        		"B2:Elektrodynamik:Kelvin:34");
        pw.close();


	    Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
  		System.out.println(al);
  		System.out.println(" <=> " + l.titles());
	}

	public void Workaholics() throws IOException, TextFileFormatException
	{
		Set<String> s = new HashSet<String>();

		s.add("Planck");
		s.add("Kelvin");

		pw.print(
        		"A1:Relativitaetstheorie:Einstein:15\n" +
        		"B2:Quantenmechanik:Heisenberg:17\n" +
        		"C2:Quantenphysik:Planck:5\n" +
        		"T4:Thermodynamik:Kelvin:78\n" +
        		"C2:Theoretische Physik:Kelvin:54\n" +
        		"B2:Thermodynamik:Planck:44\n" +
        		"T4:Quantenphysik:Planck:45\n" +
        		"B2:Elektrodynamik:Kelvin:34");
        pw.close();


		Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
  		System.out.println(s);
  		System.out.println(" <=> " + l.workaholics());
	}
	
	public void GroupToTitles() throws IOException, TextFileFormatException
	{
        Map<String, List<String>> map2 = new HashMap<String, List<String>>();
        List<String> s1 = new ArrayList<String>();
        List<String> s2 = new ArrayList<String>();
        List<String> s3a = new ArrayList<String>();
        List<String> s3b = new ArrayList<String>();
        List<String> s4a = new ArrayList<String>();
        List<String> s4b = new ArrayList<String>();
        
        s1.add("Relativitaetstheorie");
		map2.put("A1",s1);
        s2.add("Quantenmechanik");
        s2.add("Thermodynamik");
        s2.add("Elektrodynamik");
		map2.put("B2",s2);
        s3a.add("Quantenphysik");
        s3a.add("Theoretische Physik");
        s3b.add("Theoretische Physik");
        s3b.add("Quantenphysik");
		map2.put("C2",s3a);
        s4a.add("Thermodynamik");
        s4a.add("Quantenphysik");
		map2.put("T4",s4a);
        s4b.add("Quantenphysik");
        s4b.add("Thermodynamik");

		pw.print(
        		"A1:Relativitaetstheorie:Einstein:15\n" +
        		"B2:Quantenmechanik:Heisenberg:17\n" +
        		"C2:Quantenphysik:Planck:5\n" +
        		"T4:Thermodynamik:Kelvin:78\n" +
        		"C2:Theoretische Physik:Kelvin:54\n" +
        		"B2:Thermodynamik:Planck:44\n" +
        		"T4:Quantenphysik:Planck:45\n" +
        		"B2:Elektrodynamik:Kelvin:34");
        pw.close();

		Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
  		System.out.println(map2);
  		System.out.println(" <=> " + l.groupToTitles());
	}

	public void multipleTitles() throws IOException, TextFileFormatException
	{
        Map<String, List<String>> map2 = new HashMap<String, List<String>>();
        List<String> s1a = new ArrayList<String>();
        List<String> s1b = new ArrayList<String>();
        
        s1a.add("Kelvin");
        s1a.add("Planck");
        s1b.add("Planck");
        s1b.add("Kelvin");
		map2.put("Thermodynamik",s1a);
		
		pw.print(
        		"A1:Relativitaetstheorie:Einstein:15\n" +
        		"B2:Quantenmechanik:Heisenberg:17\n" +
        		"C2:Quantenphysik:Planck:5\n" +
        		"T4:Thermodynamik:Kelvin:78\n" +
        		"C2:Theoretische Physik:Kelvin:54\n" +
        		"B2:Thermodynamik:Planck:44\n" +
        		"T4:Quantenphysik:Planck:45\n" +
        		"B2:Elektrodynamik:Kelvin:34");
        pw.close();


		Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
		System.out.println(map2);
		System.out.println(" <=> " + l.multipleTitles());
	}
	
	public void descendingTitles() throws IOException, TextFileFormatException
	{
		{
		    List<String> al = new ArrayList<String>();

		    al.add("Thermodynamik");
			al.add("Theoretische Physik");
			al.add("Quantenphysik");
			al.add("Thermodynamik");
		    al.add("Elektrodynamik");
		    al.add("Quantenmechanik");
	        al.add("Relativitaetstheorie");
			al.add("Quantenphysik");

			pw.print(
	        		"A1:Relativitaetstheorie:Einstein:15\n" +
	        		"B2:Quantenmechanik:Heisenberg:17\n" +
	        		"C2:Quantenphysik:Planck:5\n" +
	        		"T4:Thermodynamik:Kelvin:78\n" +
	        		"C2:Theoretische Physik:Kelvin:54\n" +
	        		"B2:Thermodynamik:Planck:44\n" +
	        		"T4:Quantenphysik:Planck:45\n" +
	        		"B2:Elektrodynamik:Kelvin:34");
	        pw.close();

		    Vorlesungsverzeichnis l = new Vorlesungsverzeichnis(filename);
  			System.out.println(al);
  			System.out.println(" <=> " + l.descendingTitles());
		}
	}

    public static void main(String[] args) {
        try {
            App app = new App();
            app.setUp();
            app.Titles();
            app.Workaholics();
            app.GroupToTitles();
            app.multipleTitles();
            app.descendingTitles();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (TextFileFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
