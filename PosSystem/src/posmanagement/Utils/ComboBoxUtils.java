package posmanagement.Utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListCell;

public class ComboBoxUtils<T> extends ListCell<T> {

    private final StringProperty promptText = new SimpleStringProperty();

    public ComboBoxUtils(String promptText) {
        setPromptText(promptText);
        initialize();
    }

    private void initialize() {
        promptTextProperty().addListener((obs, oldText, newText) -> setText(isEmpty() || getItem() == null ? newText : getItem().toString()));
    }

    public StringProperty promptTextProperty() {
        return promptText;
    }

    public final String getPromptText() {
        return promptTextProperty().get();
    }

    public final void setPromptText(String promptText) {
        promptTextProperty().set(promptText);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty || item == null ? getPromptText() : item.toString());
    }
}
