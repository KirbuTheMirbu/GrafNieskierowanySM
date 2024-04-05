import java.util.Random;
import java.util.*;
import java.util.Map;

public static void main(String[] args) {
    Map<Integer, Integer> punktyZWartosciami = new HashMap<>();
    punktyZWartosciami.put(0, 10);
    punktyZWartosciami.put(1, 20);
    punktyZWartosciami.put(2, 30);
    punktyZWartosciami.put(3, 40);
    punktyZWartosciami.put(4, 50);

    Graph graph = new Graph(5, punktyZWartosciami);
    graph.dodajKrawedz(0,1);
    graph.dodajKrawedz(0,3);
    graph.dodajKrawedz(1,2);
    graph.dodajKrawedz(3,4);
    graph.dodajKrawedz(2,4);

    graph.wyswietlGraf();
    System.out.println();

    graph.usunKrawedz(0, 1);
    System.out.println("Po usunięciu krawędzi (0,1):");
    graph.wyswietlGraf();
    System.out.println();

    graph.usunPunktIKrawedzie(0);
    System.out.println("Po usunięciu wierzchołka 0 i powiązanych krawędzi:");
    graph.wyswietlGraf();
    System.out.println();

    Map<Integer, Integer> wartosciWierzcholkow = graph.pobierzWartosciWierzcholkow();
    for (Map.Entry<Integer, Integer> entry : wartosciWierzcholkow.entrySet()) {
        System.out.println("Wierzchołek " + entry.getKey() + " ma wartość: " + entry.getValue());
    }
}


