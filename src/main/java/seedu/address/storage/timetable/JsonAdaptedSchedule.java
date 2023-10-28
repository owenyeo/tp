package seedu.address.storage.timetable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.timetable.Cca;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Module;
import seedu.address.model.person.timetable.Schedule;

public class JsonAdaptedSchedule {
    List<JsonAdaptedDatedEvent> datedEvents = new ArrayList<>();
    List<JsonAdaptedModule> modules = new ArrayList<>();
    List<JsonAdaptedCca> ccas = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("datedEvents") List<JsonAdaptedDatedEvent> datedEvents,
                               @JsonProperty("modules") List<JsonAdaptedModule> modules,
                               @JsonProperty("cca") List<JsonAdaptedCca> cca) {
        this.datedEvents = datedEvents;
        this.modules = modules;
        this.ccas = cca;
    }

    public JsonAdaptedSchedule(Schedule source) {
        List<DatedEvent> datedEventList = source.getDatedEventsList();
        List<Module> modulesList = source.getModulesList();
        List<Cca> ccaList = source.getCcasList();

        for (DatedEvent datedEvent : datedEventList) {
            datedEvents.add(new JsonAdaptedDatedEvent(datedEvent));
        }

        for (Module module : modulesList) {
            modules.add(new JsonAdaptedModule(module));
        }

        for (Cca cca : ccaList) {
            ccas.add(new JsonAdaptedCca(cca));
        }
    }

    public Schedule toModelType() {
        List<DatedEvent> datedEventList = new ArrayList<>();
        List<Module> modulesList = new ArrayList<>();
        List<Cca> ccaList = new ArrayList<>();

        for (JsonAdaptedDatedEvent datedEvent : datedEvents) {
            try {
                datedEventList.add(datedEvent.toModelType());
            } catch (Exception e) {
                continue;
            }
        }

        for (JsonAdaptedModule module : modules) {
            modulesList.add(module.toModelType());
        }

        for (JsonAdaptedCca cca : ccas) {
            ccaList.add(cca.toModelType());
        }

        return new Schedule(modulesList, ccaList, datedEventList);
    }
}
