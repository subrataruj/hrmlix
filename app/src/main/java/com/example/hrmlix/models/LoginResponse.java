package com.example.hrmlix.models;

public class LoginResponse {

            private boolean success;
            private boolean message;

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }

            public boolean getMessage() {
                return message;
            }

            public void setMessage(boolean message) {
                this.message = message;
            }

}
