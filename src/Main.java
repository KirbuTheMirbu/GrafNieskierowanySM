import java.util.Random;
import java.util.*;
import java.util.Map;

public static void main(String[] args) {
    System.out.println("Liczby w nawiasach obok wierzchołków oznaczają ich wartości.");
    System.out.println();
    Map<Integer, Integer> punktyZWartosciami = new HashMap<>();
    punktyZWartosciami.put(0, 10);
    punktyZWartosciami.put(1, 20);
    punktyZWartosciami.put(2, 30);
    punktyZWartosciami.put(3, 40);
    punktyZWartosciami.put(4, 50);

    Graph graph = new Graph(5, punktyZWartosciami);
    graph.dodajKrawedz(0,1, 1);
    graph.dodajKrawedz(0,3, 2);
    graph.dodajKrawedz(1,2, 3);
    graph.dodajKrawedz(3,4, 4);
    graph.dodajKrawedz(2,4, 5);

    Map<Integer, Integer> odleglosci = graph.dijkstra(0);
    graph.wyswietlGraf(odleglosci);
    System.out.println();

    graph.pobierzWartosciWszystkichWierzcholkow();
    System.out.println();

    graph.usunKrawedz(0, 1);
    System.out.println("Po usunięciu krawędzi (0,1):");
    graph.wyswietlGraf(odleglosci);
    System.out.println();

    graph.usunPunktIKrawedzie(0);
    System.out.println("Po usunięciu wierzchołka 0 i powiązanych krawędzi:");
    graph.wyswietlGraf(odleglosci);
    System.out.println();

    graph.pobierzWartosciWszystkichWierzcholkow();

    System.out.println();
    System.out.println("Minimalne drzewo rozpinające (MST):");
    List<int[]> mst = graph.minimalneDrzewoRozpinajace();
    for (int[] krawedz : mst) {
        System.out.println("Krawędź: " + krawedz[0] + " - " + krawedz[1] + ", Waga: " + krawedz[2]);
    }

    System.out.println("\nMinimalne drzewo rozpinające (MST) metoda Prima:");
    List<int[]> mstPrima = graph.minimalneDrzewoRozpinajacePrima();
    for (int[] krawedz : mstPrima) {
        System.out.println("Krawędź: " + krawedz[0] + " - " + krawedz[1] + ", Waga: " + krawedz[2]);
    }

    int minimalnaLiczbaChromatyczna = graph.minimalnaLiczbaChromatyczna();
    System.out.println();
    System.out.println("Minimalna liczba chromatyczna: " + minimalnaLiczbaChromatyczna);

}
