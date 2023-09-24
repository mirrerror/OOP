package md.mirrerror.entities;

public enum StudyField {
    MECHANICAL_ENGINEERING, SOFTWARE_ENGINEERING, FOOD_TECHNOLOGY, URBANISM_ARCHITECTURE, VETERINARY_MEDICINE;

    public static StudyField match(String s) {
        return StudyField.valueOf(s.toUpperCase());
    }
}
