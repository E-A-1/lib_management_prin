package domain;

import java.util.Date;

public class BookReturn {
    private int requestId;
    private String returnStatus;

    public BookReturn(int requestId, String returnStatus, Date returnDate) {
        this.requestId = requestId;
        this.returnStatus = returnStatus;
        this.returnDate = returnDate;
    }

    private Date returnDate;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}
