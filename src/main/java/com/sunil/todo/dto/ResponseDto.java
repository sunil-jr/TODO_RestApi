package com.sunil.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object data;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String builderMessage;
        private Object builderData;

        public Builder message(String message) {
            this.builderMessage = message;
            return this;
        }

        public Builder data(Object data) {
            this.builderData = data;
            return this;
        }

        public ResponseDto build() {
            return new ResponseDto(builderMessage, builderData);
        }
    }

}
