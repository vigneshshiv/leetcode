package code.ctci.features;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CountryPopulation {

    /**
     * Get Country population by continent
     */
    static long getPopulation(List<Country> countries, String continent) {
        Predicate<Country> continentBasedCountry = country -> country.getContinent().equals(continent);
        Function<Country, Long> populationDetail = country -> country.getPopulation();
        Long population = countries.stream().filter(continentBasedCountry).map(populationDetail).reduce(0L, Long::sum);
        return population;
    }

    public static void main(String[] args) {
        List<Country> countries = new ArrayList<>();
        // America
        Country northAmerica = new Country("NorthAmerica", 13302480234L, "America");
        Country southAmerica = new Country("SouthAmerica", 2342342302L, "America");
        Country mexico = new Country("Mexico", 234234213L, "America");
        Country canada = new Country("Canada", 3202314311L, "America");
        //
        countries.add(northAmerica);
        countries.add(southAmerica);
        countries.add(mexico);
        countries.add(canada);
        // Asia
        Country india = new Country("India", 12234232323L, "Asia");
        Country china = new Country("China", 16799024976L, "Asia");
        Country singapore = new Country("Singapore", 1434323434L, "Asia");
        Country malaysia = new Country("Malaysia", 34534343224L, "Asia");
        //
        countries.add(india);
        countries.add(china);
        countries.add(singapore);
        countries.add(malaysia);
        // Europe
        Country france = new Country("France", 893234923L, "Europe");
        Country germany = new Country("Germany", 3449345934L, "Europe");
        Country italy = new Country("Italy", 5609392834L, "Europe");
        Country ireland = new Country("Ireland", 849583483L, "Europe");
        //
        countries.add(france);
        countries.add(germany);
        countries.add(italy);
        countries.add(ireland);
        // Get Continents of Asia
        long asiaPopulation = getPopulation(countries, "Asia");
        System.out.println("Asia Population - " + asiaPopulation);
    }

}

class Country {
    private String name;
    private long population;
    private String continent;

    public Country(String name, long population, String continent) {
        this.name = name;
        this.population = population;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }
}
