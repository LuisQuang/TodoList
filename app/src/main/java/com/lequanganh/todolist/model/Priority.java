package com.lequanganh.todolist.model;

    public enum Priority {
        LOW("1"), MEDIUM("2"), HIGH("3"), VERY_HIGH("4");
        private String code;

        private Priority(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

}
