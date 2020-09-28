package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedLocation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLocations.BENSON_LOCATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.person.Name;

public class JsonAdaptedLocationTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";

    private static final String VALID_NAME = BENSON_LOCATION.getName().toString();

    @Test
    public void toModelType_validLocationDetails_returnsLocation() throws Exception {
        JsonAdaptedLocation location = new JsonAdaptedLocation(BENSON_LOCATION);
        assertEquals(BENSON_LOCATION, location.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedLocation location =
                new JsonAdaptedLocation(INVALID_NAME);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, location::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        String name = null;
        JsonAdaptedLocation location = new JsonAdaptedLocation(name);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, location::toModelType);
    }
}
