package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Location}.
 */
public class JsonAdaptedLocation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Location's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedLocation(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Location} into this class for Jackson use.
     */
    public JsonAdaptedLocation(Location source) {
        name = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted location object into the model's {@code Location} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted location.
     */
    public Location toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        return new Location(modelName);
    }
}
