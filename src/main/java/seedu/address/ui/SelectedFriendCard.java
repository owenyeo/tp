package seedu.address.ui;

import java.util.Comparator;
import java.util.concurrent.Flow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;


public class SelectedFriendCard extends UiPart<Region> {
    private static final String FXML = "SelectedFriendCard.fxml";

    public final Person selectedFriend;

    @javafx.fxml.FXML
    private Label userName;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane freeTimes;
    @FXML
    private FlowPane tags;

    public SelectedFriendCard(Person selectedFriend) {
        super(FXML);
        this.selectedFriend = selectedFriend;
        userName.setText(selectedFriend.getName().fullName);
        phone.setText(selectedFriend.getPhone().value);
        address.setText(selectedFriend.getAddress().value);
        email.setText(selectedFriend.getEmail().value);
        selectedFriend.getFreeTimes().stream()
                .sorted(Comparator.comparing(freeTime -> freeTime.freeTimeString))
                .forEach(freeTime -> freeTimes.getChildren().add(new Label(freeTime.freeTimeString)));
        selectedFriend.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
