package vorlesung;

import java.util.List;
import java.util.ArrayList;

public class Vorlesung {
    public static final int FIELDS = 4;

    private String group;
    private String title;
    private String prof;
    private int participants;

    public Vorlesung(List<String> list) throws NumberFormatException {
        this(list.get(0), list.get(1), list.get(2), Integer.parseInt(list.get(3)));
    }

    public Vorlesung(
        String group,
        String title,
        String prof,
        int participants
    ) {
        this.group = group;
        this.title = title;
        this.prof = prof;
        this.participants = participants;
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>();

        list.add(this.group);
        list.add(this.title);
        list.add(this.prof);
        list.add(String.valueOf(this.participants));

        return list;
    }

    public String getGroup() {
        return this.group;
    }

    public String getTitle() {
        return this.title;
    }

    public String getProf() {
        return this.prof;
    }

    public int getParticipants() {
        return this.participants;
    }

    public int hashCode() {
        return this.group.hashCode() +
            this.title.hashCode() +
            this.prof.hashCode() +
            ((Integer) this.participants).hashCode();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Vorlesung(group=");
        str.append(this.group);
        str.append(", title=");
        str.append(this.title);
        str.append(", prof=");
        str.append(this.prof);
        str.append(", participants=");
        str.append(this.participants);
        str.append(")");

        return str.toString();
    }
}
