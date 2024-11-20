package ch.heigvd.dai.Types;

public class Message {
    private final User editor;
    private final Date date;
    private final Time time;
    private final String content;

    public Message(User editor, String content) {
        this.editor = editor;

        // Initialisation et mise à jour de la date et de l'heure
        this.date = new Date();
        this.time = new Time();
        this.date.updateToCurrentSystemDate();
        this.time.updateToCurrentSystemTime();

        // Création de l'en-tête
        this.content = String.format("%s - %s - %s -> %s",
                this.date.getFullDate(),
                this.time.getFullTime(),
                editor.getName(),
                content);
    }

    public User getEditor() {
        return editor;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}
