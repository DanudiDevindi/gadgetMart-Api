package com.bolton.gadgetmart_main.api.dto.response;

public class StandardResponse {
	
	private boolean state;
    private String message;

    public StandardResponse() {
    }

    public StandardResponse(boolean state, String message) {
        this.setState(state);
        this.setMessage(message);
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StandardResponse{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
