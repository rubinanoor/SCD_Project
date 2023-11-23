package models;

import java.util.Stack;
public class Textfileoperations {

    private Stack<Edit> undoStack;
    private Stack<Edit> redoStack;
    private Stack<String> clipboardStack;

    public enum EditType {
        INSERT, DELETE
    }

    protected class Edit {
        String contentBefore;
        String contentAfter;
        EditType type;
        int editPosition;

        public Edit(String before, String after, EditType type, int pos) {
            this.contentBefore = before;
            this.contentAfter = after;
            this.type = type;
            this.editPosition = pos;
        }
    }

    // ... [Rest of the methods remain unchanged]

    public void onInsert(String contentBefore, String contentAfter, int insertPosition) {
        undoStack.push(new Edit(contentBefore, contentAfter, EditType.INSERT, insertPosition));
        redoStack.clear();
    }

    public void onDelete(String contentBefore, String contentAfter, int deletePosition) {
        undoStack.push(new Edit(contentBefore, contentAfter, EditType.DELETE, deletePosition));
        redoStack.clear();
    }
    public Textfileoperations() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.clipboardStack = new Stack<>();
    }

    public String cut(String content, int startIndex, int endIndex) {
        String cutText = content.substring(startIndex, endIndex);
        String updatedContent = content.substring(0, startIndex) + content.substring(endIndex);
        pushToClipboardStack(cutText);
        // Push the state before the cut to the undo stack
        onEdit(content, updatedContent, startIndex);
        return updatedContent;
    }

    public String copy(String content, int startIndex, int endIndex) {
        String copiedText = content.substring(startIndex, endIndex);
        pushToClipboardStack(copiedText);
        return content; // Copy does not change the content, no need to push to undo stack
    }

    public String paste(String originalContent, int insertionIndex) {
        if (!clipboardStack.isEmpty()) {
            String clipboardContent = clipboardStack.peek(); // Get the top item without removing it
            String updatedContent = originalContent.substring(0, insertionIndex) + clipboardContent + originalContent.substring(insertionIndex);
            // Push the state before the paste to the undo stack
            onEdit(originalContent, updatedContent, insertionIndex);
            return updatedContent;
        }
        return originalContent; // Clipboard is empty, nothing to paste
    }

    public void onEdit(String contentBefore, String contentAfter, int editPosition) {
        undoStack.push(new Edit(contentBefore, contentAfter, EditType.INSERT,editPosition));
        redoStack.clear(); // Clear redo stack when a new action is performed
    }

    public Edit undo() {
        if (!undoStack.isEmpty()) {
            Edit lastEdit = undoStack.pop();
            redoStack.push(lastEdit);
            return lastEdit;
        }
        return null;
    }

    public Edit redo() {
        if (!redoStack.isEmpty()) {
            Edit nextEdit = redoStack.pop();
            undoStack.push(nextEdit);
            return nextEdit;
        }
        return null;
    }

    private void pushToClipboardStack(String text) {
        clipboardStack.push(text);
    }

    // Additional text editing operations can be added here
    public String getClipboardContent() {
        if (!clipboardStack.isEmpty()) {
            return clipboardStack.peek();
        }
        return ""; // Return an empty string if clipboard is empty
    }

}
