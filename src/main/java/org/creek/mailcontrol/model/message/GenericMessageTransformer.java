package org.creek.mailcontrol.model.message;

import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * @author Andrey Pereverzin
 */
public class GenericMessageTransformer {
    private final JSONParser parser;
    private final JSONTransformer transformer;

    public GenericMessageTransformer() {
        this.parser = new JSONParser();
        this.transformer = new JSONTransformer();
    }

    public GenericMessageTransformer(JSONParser parser, JSONTransformer transformer) {
        this.parser = parser;
        this.transformer = transformer;
    }

    public GenericMessage transform(String content) throws TransformException {
        MessageType messageType;

        try {
            parser.parse(content, transformer);

            JSONObject jsonObject = (JSONObject) transformer.getResult();
            messageType = MessageType.getMessageType(Integer.parseInt((String) jsonObject.get(AbstractMessage.MESSAGE_TYPE)));

            if (messageType == MessageType.ITEM_COMMAND_REQUEST_MESSAGE) {
                return new ItemCommandRequestMessage(jsonObject);
            } else {
                throw new TransformException("Unknown message type " + messageType);
            }
        } catch (Exception ex) {
            throw new TransformException(ex);
        }
    }
}
