package org.creek.mailcontrol.model.data;

import static org.creek.mailcontrol.model.data.AbstractData.TYPE;
import static org.creek.mailcontrol.model.data.DataType.DATE_TIME;
import static org.creek.mailcontrol.model.data.DataType.DECIMAL;
import static org.creek.mailcontrol.model.data.DataType.HSB;
import static org.creek.mailcontrol.model.data.DataType.ON_OFF;
import static org.creek.mailcontrol.model.data.DataType.OPEN_CLOSED;
import static org.creek.mailcontrol.model.data.DataType.PERCENT;
import static org.creek.mailcontrol.model.data.DataType.STRING;
import static org.creek.mailcontrol.model.data.DataType.UP_DOWN;

import java.util.ArrayList;
import java.util.List;

import org.creek.mailcontrol.model.message.ItemData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author Andrey Pereverzin
 */
@SuppressWarnings("serial")
public class ItemStateData extends ItemData {
    private final StateTransformable state;
    private final List<DataType> acceptedCommands;
    
    private static final String STATE = "state";
    private static final String ACCEPTED_COMMANDS = "acceptedCommands";

    public ItemStateData(long timeSent, String itemId, StateTransformable state, List<DataType> acceptedCommands) {
        super(timeSent, itemId);
        this.state = state;
        this.acceptedCommands = acceptedCommands;
    }

    public ItemStateData(JSONObject jsonObject) {
        super(jsonObject);
        this.state = buildState(jsonObject);
        this.acceptedCommands = buildAcceptedCommandsList(jsonObject);
    }

    public StateTransformable getState() {
        return state;
    }

    public List<DataType> getAcceptedCommands() {
        return acceptedCommands;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put(STATE, state.toJSON());
        JSONArray acceptedCommandsArray = new JSONArray();
        for (int i = 0; i < acceptedCommands.size(); i++) {
            DataType command = acceptedCommands.get(i);
            acceptedCommandsArray.add(command.name());
        }
        jsonObject.put(ACCEPTED_COMMANDS, acceptedCommandsArray);
        return jsonObject;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [" + super.toString() + ", " + STATE + "=" + state + "]";
    }

    private StateTransformable buildState(JSONObject jsonObject) {
        JSONObject stateObject = (JSONObject)jsonObject.get(STATE);
        DataType stateType = DataType.valueOf((String)stateObject.get(TYPE));
        if (stateType == DATE_TIME) {
            return new DateTimeData(stateObject);
        }
        if (stateType == DECIMAL) {
            return new DecimalData(stateObject);
        }
        if (stateType == HSB) {
            return new HSBData(stateObject);
        }
        if (stateType == ON_OFF) {
            return new OnOffData(stateObject);
        } 
        if (stateType == OPEN_CLOSED) {
            return new OpenClosedData(stateObject);
        } 
        if (stateType == PERCENT) {
            return new PercentData(stateObject);
        } 
        if (stateType == STRING) {
            return new StringData(stateObject);
        } 
        if (stateType == UP_DOWN) {
            return new UpDownData(stateObject);
        }
        throw new IllegalArgumentException();
    }
    
    private List<DataType> buildAcceptedCommandsList(JSONObject jsonObject) {
        List<DataType> acceptedCommands = new ArrayList<DataType>();
        
        JSONArray acceptedCommandsArray = (JSONArray)jsonObject.get(ACCEPTED_COMMANDS);
        for(Object acceptedCommandObject: acceptedCommandsArray) {
            DataType acceptedCommand = DataType.valueOf(acceptedCommandObject.toString());
            acceptedCommands.add(acceptedCommand);
        }
        
        return acceptedCommands;
    }
}
