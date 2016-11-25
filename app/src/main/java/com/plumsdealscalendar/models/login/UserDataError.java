package com.plumsdealscalendar.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NickNb on 25.11.2016.
 */
public class UserDataError {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("payload")
    @Expose
    private String payload;
    @SerializedName("error")
    @Expose
    private List<Error> error = new ArrayList<Error>();

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     *
     * @param payload
     * The payload
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     *
     * @return
     * The error
     */
    public List<Error> getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(List<Error> error) {
        this.error = error;
    }

    public class Error {

        @SerializedName("field")
        @Expose
        private String field;
        @SerializedName("message")
        @Expose
        private String message;

        /**
         *
         * @return
         * The field
         */
        public String getField() {
            return field;
        }

        /**
         *
         * @param field
         * The field
         */
        public void setField(String field) {
            this.field = field;
        }

        /**
         *
         * @return
         * The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

    }

}
