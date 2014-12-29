package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.MESSAGE_TYPE;
import static org.creek.mailcontrol.model.message.MessageType.ITEMS_STATE_REQUEST_MESSAGE;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_COMMAND_REQUEST_MESSAGE;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_STATE_REQUEST_MESSAGE;

import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * @author Andrey Pereverzin
 */
public class GenericRequestTransformer {
    private final JSONParser parser;
    private final JSONTransformer transformer;

    public GenericRequestTransformer() {
        this.parser = new JSONParser();
        this.transformer = new JSONTransformer();
    }

    public GenericRequest transform(String content) throws TransformException {
        MessageType messageType;

        try {
            parser.parse(content, transformer);

            JSONObject jsonObject = (JSONObject) transformer.getResult();
            messageType = MessageType.getMessageType(Integer.parseInt((String) jsonObject.get(MESSAGE_TYPE)));

            if (messageType == ITEM_COMMAND_REQUEST_MESSAGE) {
                return new ItemCommandRequestMessage(jsonObject);
            } else if (messageType == ITEM_STATE_REQUEST_MESSAGE) {
                return new ItemStateRequestMessage(jsonObject);
            } else if (messageType == ITEMS_STATE_REQUEST_MESSAGE) {
                return new ItemsStateRequestMessage(jsonObject);
            } else {
                throw new TransformException("Unknown request type " + messageType);
            }
        } catch (Exception ex) {
            throw new TransformException(ex);
        }
    }
}
