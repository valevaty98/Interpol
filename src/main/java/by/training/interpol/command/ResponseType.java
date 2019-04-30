package by.training.interpol.command;

public class ResponseType {
    private String page;
    private SendType sendType;

    public SendType getSendType() {
        return sendType;
    }

    public void setType(SendType sendType) {
        this.sendType = sendType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ResponseType(String page, SendType sendType) {
        this.page = page;
        this.sendType = sendType;
    }
}
