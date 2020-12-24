package round2.oa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 12/16/2020 4:17 PM
 * @topic round2.oa
 * @link
 * @description Dropbox OA Attempted solution
 */
public class TextEditor {
    String[][] input;
    StringBuilder sb;
    Stack<Command> undoStack;
    Stack<Command> redoStack;
    int start;
    int end;

    public TextEditor() {
        sb = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public String textEditor(String[][] input) {
        this.input = input;
        Arrays.sort(input, (a, b) -> (int) (Long.parseLong(a[0]) - Long.parseLong(b[0])));

        Arrays.sort(input, Comparator.comparingInt(a -> Integer.parseInt(a[0])));

        for (int i = 0; i < input.length; i++) {
            String[] current = input[i];
            switch (current[1]) {
                case "APPEND" -> {
                    String appendString = current[2];
                    Command command;
                    // insert in range
                    if (i != 0 && input[i - 1][1].equals("SELECT")) {
                        command = new InsertCommand(sb, appendString, this.start, this.end);
                    } else { // simply append
                        command = new AppendCommand(sb, appendString);
                    }
                    command.doCommand();
                }
                case "BACKSPACE" -> {
                    Command command;
                    if (i != 0 && input[i - 1][1].equals("SELECT")) {
                        command = new DeleteCommand(sb, this.start, this.end);
                    } else {
                        command = new BackCommand(sb);
                    }
                    command.doCommand();
                }
                case "UNDO" -> {
                    if (undoStack.isEmpty()) continue;
                    Command command = undoStack.pop();
                    command.undoCommand();
                }
                case "REDO" -> {
                    if (redoStack.isEmpty()) continue;
                    Command command = redoStack.pop();
                    command.doCommand();
                }
                case "SELECT" -> {
                    select(current[2], current[3]);
                }
                case "BOLD" -> {
                    if (i != 0 && input[i - 1][1].equals("SELECT")) {
                        Command boldCommand = new BoldCommand(sb, this.start, this.end);
                        boldCommand.doCommand();
                    }
                }
            }
        }
        return sb.toString();
    }

    private void select(String left, String right) {
        int start = Integer.parseInt(left);
        if (start >= sb.length()) return;
        this.start = start;

        int end = Integer.parseInt(right);
        this.end = Math.min(sb.length(), end);
    }

    public abstract static class Command {
        abstract void doCommand();
        abstract void undoCommand();
    }

    public class AppendCommand extends Command {
        StringBuilder sb;
        String appendString;

        public AppendCommand(StringBuilder sb, String appendString) {
            this.sb = sb;
            this.appendString = appendString;
        }
        @Override
        void doCommand() {
            sb.append(appendString);
            undoStack.push(this);
        }

        @Override
        void undoCommand() {
            sb.setLength(sb.length() - appendString.length());
            redoStack.push(this);
        }
    }

    public class InsertCommand extends Command {
        StringBuilder sb;
        String cachedString;
        String insertString;
        //String replacedString;
        int start;
        int end;

        public InsertCommand(StringBuilder sb, String insertString, int start, int end) {
            this.sb = sb;
            this.insertString = insertString;
            this.start = start;
            this.end = end;
        }
        @Override
        void doCommand() {
            cachedString = sb.toString();
            sb.replace(start, end, "");
            sb.insert(start, insertString);
            undoStack.push(this);
        }

        @Override
        void undoCommand() {
            // revert to cached string
            sb.replace(0, sb.length(), cachedString);
            redoStack.push(this);
        }
    }

    public class BackCommand extends Command {
        StringBuilder sb;
        String deleteCharacter;

        public BackCommand(StringBuilder sb) {
            this.sb = sb;
        }
        @Override
        void doCommand() {
            deleteCharacter = String.valueOf(sb.charAt(sb.length() -1));
            sb.deleteCharAt(sb.length() - 1);
            undoStack.push(this);
        }

        @Override
        void undoCommand() {
            sb.append(deleteCharacter);
            redoStack.push(this);
        }
    }

    public class DeleteCommand extends Command {
        StringBuilder sb;
        String cachedString;
        int start;
        int end;

        public DeleteCommand(StringBuilder sb, int start, int end) {
            this.sb = sb;
            this.start = start;
            this.end = end;
        }

        @Override
        void doCommand() {
            cachedString = sb.toString();
            sb.replace(start, end, "");
            undoStack.push(this);
        }

        @Override
        void undoCommand() {
            sb.replace(0, sb.length(), cachedString);
            redoStack.push(this);
        }
    }

    public class BoldCommand extends Command {
        StringBuilder sb;
        String cachedString;
        int start;
        int end;

        public BoldCommand (StringBuilder sb, int start, int end) {
            this.sb = sb;
            this.start = start;
            this.end = end;
        }
        @Override
        void doCommand() {
            cachedString = sb.toString();
            sb.insert(end, "*");
            sb.insert(start, "*");
            undoStack.push(this);
        }

        @Override
        void undoCommand() {
            sb.replace(0, sb.length(), cachedString);
            redoStack.push(this);
        }
    }

    public static void main(String[] args) {
        String[][] input = new String[][]{
                {"0", "APPEND", "HELLO"},
                {"1", "APPEND", " THERE"},
                {"2", "SELECT", "1", "3"},
                {"3", "BOLD"},
                {"5" , "UNDO"},
                {"6", "REDO"},
        };
        TextEditor textEditor = new TextEditor();
        String output = textEditor.textEditor(input);
        System.out.println("Output has length " + output.length());
        System.out.println(output);
    }
}
