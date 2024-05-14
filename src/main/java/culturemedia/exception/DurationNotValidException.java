package culturemedia.exception;

import java.text.MessageFormat;

public class DurationNotValidException extends Exception{
    public DurationNotValidException(String title, Double duration){
        super(MessageFormat.format("Duration not valid for video: {0} and duration {0}", title, duration));
    }
}
