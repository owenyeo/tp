package seedu.address.storage.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.TimeBlock;
import seedu.address.model.util.SampleDataUtil;

public class JsonAdaptedCcaTest {
    private static final String INVALID_TIMEBLOCK = "monday";


    private static final String VALID_NAME = "CS2100";
    private static final String VALID_TIMEBLOCK = "Monday 1200 1300";

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedCca module = new JsonAdaptedCca(SampleDataUtil.getSampleCcas().get(0));
        assertEquals(SampleDataUtil.getSampleModules().get(0), module.toModelType());
    }

    @Test
    public void toModelType_nullModuleName_throwsException() {
        JsonAdaptedCca module = new JsonAdaptedCca(null, VALID_TIMEBLOCK);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, module::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidTimeBlock_throwsException() {
        JsonAdaptedCca module = new JsonAdaptedCca(VALID_NAME, INVALID_TIMEBLOCK);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, module::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullTimeBlock_throwsException() {
        JsonAdaptedCca module = new JsonAdaptedCca(VALID_NAME, null);
        String expectedMessage = TimeBlock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, module::toModelType, expectedMessage);
    }
}
