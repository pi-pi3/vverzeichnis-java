package vorlesung;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Vorlesungsverzeichnis {
    private Set<Vorlesung> set;

    public Vorlesungsverzeichnis(String filename)
            throws TextFileFormatException, IOException
    {
        List<List<String>> list;
        try {
            list = Vorlesungsverzeichnis.load(filename);
        } catch (IOException e) {
            throw e;
        }

        this.set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> line = list.get(i);

            boolean emptyField = line.stream()
                .anyMatch((String s) -> s.isEmpty());

            if (emptyField) {
                throw new TextFileFormatException(filename, i);
            }

            if (line.size() != Vorlesung.FIELDS) {
                throw new TextFileFormatException(filename, i);
            }

            try {
                this.set.add(new Vorlesung(line));
            } catch (NumberFormatException e) {
                throw new TextFileFormatException(filename, i);
            }
        }
    }

    // Liefert eine alphabetisch sortierte Liste mit den Titeln aller
    // Vorlesungen.
    public List<String> titles() {
        List<String> list = this.set
            .stream()
            .map(Vorlesung::getTitle)
            .distinct()
            .collect(Collectors.toList());
        list.sort(String::compareTo);
        return list;
    }

    // Liefert die Menge derjenigen Dozenten, die zwei oder mehr Vorlesungen
    // halten.
    public Set<String> workaholics() {
        HashMap<String, Integer> map = new HashMap<>();

        for (Vorlesung e : this.set) {
            String key = e.getProf();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        Set<String> set = map
            .entrySet()
            .stream()
            .filter((Map.Entry<String, Integer> e) -> e.getValue() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

        return set;
    }

    // Liefert eine Map, die Studiengruppen auf Listen von Vorlesungstiteln
    // abbildet. Unter dem Schlüssel MT2 wäre für die oben angegebene Datenbasis
    // zum Beispiel als Wert die Liste [Mathematik 2, Audio-/Videotechnik] zu
    // finden.
    public Map<String, List<String>> groupToTitles() {
        HashMap<String, List<String>> map = new HashMap<>();

        for (Vorlesung e : this.set) {
            String key = e.getGroup();
            if (map.containsKey(key)) {
                map.get(key).add(e.getTitle());
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(e.getTitle());
                map.put(key, list);
            }
        }

        return map;
    }

    // Liefert eine Map, die Vorlesungen auf Listen von Dozenten, die diese
    // Vorlesungen halten, abbildet. Als Schlüssel werden in der Map nur
    // Vorlesungen verwendet, die von unterschiedlichen Dozenten gehalten
    // werden. Entsprechend der obigen Datenbasis würde in diesem Fall nur ein
    // Eintrag in der Map stehen mit dem Schlüssel Mathematik 2 und dem Wert
    // [von Coelln, Rabe] als Liste.
    public Map<String, List<String>> multipleTitles() {
        HashMap<String, List<String>> map = new HashMap<>();

        for (Vorlesung e : this.set) {
            String key = e.getTitle();
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                boolean has = list.stream()
                    .anyMatch((String s) -> s.equals(e.getProf()));
                if (!has) {
                    list.add(e.getProf());
                }
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(e.getProf());
                map.put(key, list);
            }
        }

        return map.entrySet()
            .stream()
            .filter((Map.Entry<String, List<String>> e) -> e.getValue().size() > 1)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Liefert eine nach Teilnehmerzahl absteigend(!) sortierte Liste mit den
    // Titeln aller Vorlesungen.
    public List<String> descendingTitles() {
        List<Vorlesung> list = this.set.stream().collect(Collectors.toList());
        list.sort((Vorlesung a, Vorlesung b) -> b.getParticipants() - a.getParticipants());
        return list.stream()
            .map(Vorlesung::getTitle)
            .collect(Collectors.toList());
    }

    public String toString() {
        return this.set.toString();
    }

    public static List<List<String>> load(String filename) throws IOException {
        List<List<String>> result = new ArrayList<List<String>>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(Arrays.asList(line.split(":")));
        }

        br.close();
        return result;
    }
}
