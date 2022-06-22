public enum RomeNumber {
    I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"), VII("7"), VIII("8"), IX("9"), X("10"), XI("11"),
    XII("12"), XIII("13"), XIV("14"), XV("15"), XVI("16"), XVII("17"), XVIII("18"), XIX("19"), XX("20");

    private String arabicNumber;

    RomeNumber(String arabicNumber) {
        this.arabicNumber = arabicNumber;
    }

    public String getArabicNumber() {
        return arabicNumber;
    }

    public static RomeNumber getRomeNumber(String arabicNumber) {
        for (RomeNumber num: RomeNumber.values()) {
            if (num.arabicNumber.equals(arabicNumber)) {
                return num;
            }
        }
        return null;
    }
}
