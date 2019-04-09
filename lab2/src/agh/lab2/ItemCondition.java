package agh.lab2;

public enum ItemCondition {
    NEW {
        @Override public String toString() {
            return "new";
        }
    },
    USED {
        @Override
        public String toString() {
            return "used";
        }
    },
    REFURBISHED {
            @Override
            public String toString() {
                return "refurbished";
            }
        },
}
