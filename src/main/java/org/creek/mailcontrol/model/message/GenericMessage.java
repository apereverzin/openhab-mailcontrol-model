package org.creek.mailcontrol.model.message;

/**
 * 
 * @author Andrey Pereverzin
 */
public interface GenericMessage extends JsonTransformable {
    MessageType getMessageType();    
    String getProductVersion();
    MessageId getMessageId();
}
