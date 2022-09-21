package fs.polytech.fsback.dto;

import lombok.Data;

@Data
public class ErrorMessagesDto {
    public String code;
    public String message;

    public ErrorMessagesDto(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }


}
