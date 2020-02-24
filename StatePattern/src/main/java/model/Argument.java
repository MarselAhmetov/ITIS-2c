package model;

public class Argument {
    private Integer developerId;
    private Integer testerId;
    private String errorText;

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getTesterId() {
        return testerId;
    }

    public void setTesterId(Integer testerId) {
        this.testerId = testerId;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public static class ArgumentBuilder {
        private Argument argument;

        public ArgumentBuilder() {
            this.argument = new Argument();
        }

        public ArgumentBuilder setDeveloperId(Integer developerId) {
            argument.setDeveloperId(developerId);
            return this;
        }

        public ArgumentBuilder setTesterId(Integer testerId) {
            argument.setTesterId(testerId);
            return this;
        }

        public ArgumentBuilder setErrorText(String errorText) {
            argument.setErrorText(errorText);
            return this;
        }

        public Argument build() {
            return argument;
        }
    }
}


