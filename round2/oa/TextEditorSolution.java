package round2.oa;

import java.util.Arrays;
import java.util.Stack;

/**
 * @date 12/17/2020 1:02 AM
 * @topic round2.oa
 * @link
 * @description Dropbox Code Signal OA Accepted Solution, passed all test cases,
 * score 1000/1000
 */
public class TextEditorSolution {

    Stack<Command> undoStack;
    Stack<Command> redoStack;

    String textEditor(String[][] input) {
        StringBuilder sb = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        int[] range = new int[]{0, 0};
        Arrays.sort(input, (a, b) -> (int) (Long.parseLong(a[0]) - Long.parseLong(b[0])));
        for (int i = 0; i < input.length; i++) {
            String[] current = input[i];
            switch (current[1]) {
                case "APPEND":
                    String appendString = current[2];
                    Command addCommand;
                    if (i != 0 && input[i-1][1].equals("SELECT")) {
                        addCommand = new InsertCommand(sb, appendString, range[0], range[1]);
                    } else {
                        addCommand = new AppendCommand(sb, appendString);
                    }
                    addCommand.doCommand();
                    break;
                case "BACKSPACE":
                    Command backCommand;
                    if (i != 0 && input[i-1][1].equals("SELECT")) {
                        backCommand = new DeleteCommand(sb, range[0], range[1]);
                    } else {
                        backCommand = new BackCommand(sb);
                    }
                    backCommand.doCommand();
                    break;
                case "UNDO":
                    if (undoStack.isEmpty()) continue;
                    undoStack.pop().undoCommand();
                    break;
                case "REDO":
                    if (i == 0) continue;
                    if (input[i - 1][1].equals("REDO") || input[i - 1][1].equals("UNDO")) {
                        if (redoStack.isEmpty()) continue;
                        redoStack.pop().doCommand();
                    }
                    break;
                case "SELECT":
                    range = select(current[2], current[3], sb);
                    break;
                case "BOLD":
                    if (i != 0 && input[i - 1][1].equals("SELECT")) {
                        Command boldCommand = new BoldCommand(sb, range[0], range[1]);
                        boldCommand.doCommand();
                    }

            }
        }
        return sb.toString();
    }

    private int[] select (String left, String right, StringBuilder sb) {
        int start = Integer.parseInt(left);
        if (start >= sb.length()) return null;
        int end = Integer.parseInt(right);
        end = Math.min(sb.length(), end);
        return new int[] {start, end};
    }
    public abstract class Command {
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

    public class BackCommand extends Command {
        StringBuilder sb;
        String deleteCharacter;

        public BackCommand(StringBuilder sb) {
            this.sb = sb;
        }
        @Override
        void doCommand() {
            if (sb.length() > 0) {
                deleteCharacter = String.valueOf(sb.charAt(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
            }
            undoStack.push(this);
        }
        @Override
        void undoCommand() {
            sb.append(deleteCharacter);
            redoStack.push(this);
        }
    }

    public class InsertCommand extends Command {
        StringBuilder sb;
        String cachedString;
        String insertString;
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
            sb.replace(0, sb.length(), cachedString);
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
}
