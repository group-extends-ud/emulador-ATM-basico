package server;

import com.google.gson.Gson;

public class APOD {
    public Gson data;

    public APOD(Object data) {
        this.data = new Gson();
        System.out.println((data));
    }
}
