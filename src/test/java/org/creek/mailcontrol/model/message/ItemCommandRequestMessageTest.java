package org.creek.mailcontrol.model.message;

import static org.creek.mailcontrol.model.message.AbstractMessage.CURRENT_PRODUCT_VERSION;
import static org.creek.mailcontrol.model.message.MessageType.ITEM_COMMAND_REQUEST_MESSAGE;
import static org.junit.Assert.assertEquals;

import org.creek.mailcontrol.model.command.CommandTransformable;
import org.creek.mailcontrol.model.command.DecimalCommand;
import org.creek.mailcontrol.model.command.ItemCommand;
import org.creek.mailcontrol.model.util.JSONTransformer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Andrey Pereverzin
 */
public class ItemCommandRequestMessageTest {
    private static final long TIMESTAMP = 0L;
    private static final String ITEM_ID = "LIGHT";
    private static final String SENDER_EMAIL = "aa@bb.cc";
    private static final String DECIMAL_VALUE = "12";
    
    private ItemCommandRequestMessage message;
    private ItemCommand itemCommand;
    private CommandTransformable command;
        
    @Test
    public void shouldTransformItemCommandRequest() throws ParseException {
        // given
        command = new DecimalCommand(DECIMAL_VALUE);
        itemCommand = new ItemCommand(TIMESTAMP, ITEM_ID, command);
        message = new ItemCommandRequestMessage(itemCommand, SENDER_EMAIL);
        
        // when
        JSONObject jsonCommand = message.toJSON();
        String s = jsonCommand.toString();
        JSONParser parser = new JSONParser();
        JSONTransformer transformer = new JSONTransformer();
        parser.parse(s, transformer);

        JSONObject res = (JSONObject) transformer.getResult();
        ItemCommandRequestMessage messageRes = new ItemCommandRequestMessage(res);
        
        // then
        assertEquals(ITEM_COMMAND_REQUEST_MESSAGE, messageRes.getMessageType());
        assertEquals(CURRENT_PRODUCT_VERSION, messageRes.getProductVersion());
        assertEquals(SENDER_EMAIL, messageRes.getSenderEmail());
        assertEquals(ItemCommand.class.getName(), messageRes.getItemCommand().getClass().getName());
    }
}
