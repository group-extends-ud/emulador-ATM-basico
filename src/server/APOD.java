package server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APOD {
    public final Object data;

    public APOD(@JsonProperty("data") Object data) {
        this.data = data;
    }
}
