public enum ZodiacSign {
    Aries(21),
    Taurus(21),
    Gemini(22),
    Cancer(23),
    Leo(23),
    Virgo(23),
    Libra(23),
    Scorpio(23),
    Sagittarius(22),
    Capricorn(22),
    Aquarius(22),
    Pisces(20);

    private final int startDay;

    ZodiacSign(int startDay) {
        this.startDay = startDay;
    }

    public int startDay() {
        return this.startDay;
    }
}